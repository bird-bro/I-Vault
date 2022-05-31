package com.microworld.common.aspect;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.bird.common.tools.HttpTool;
import com.bird.common.annotation.Log;
import com.bird.common.aspect.LogHandle;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.common.Constants;
import com.microworld.vault.modules.log.entity.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author birdbro
 * @date 13:54 2022-4-28
 **/
@Component
@Aspect
@Slf4j
public class LogAop {

    @Value("${spring.application.name}")
    private String app;


    private ThreadLocal<Long> startTimeThreadLocal=new ThreadLocal<>();
    private ThreadLocal<LogEvent> logBasicsThreadLocal=new ThreadLocal<>();
    private ThreadLocal<Boolean> isPersistentThreadLocal=new ThreadLocal<>();

    @Autowired
    private LogHandle logHandle;

    @Pointcut("@annotation(com.bird.common.annotation.Log)")
    public void start(){
    }


    @Before("start()")
    public void before(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttr.getRequest();

        LogEvent logEvent = new  LogEvent();
        logEvent.setApp(app);
        logEvent.setUid((String)request.getHeader(Constants.HEAD_USER_UID));
        logEvent.setAccount((String) request.getHeader(Constants.HEAD_USER_ACCOUNT));
        logEvent.setSession(requestAttr.getSessionId());
        logEvent.setUrl(request.getRequestURI().toString());
        logEvent.setMethod(request.getMethod());
        logEvent.setIsDelete(false);
        logEvent.setCreateTime(new Date());

        HttpRequestInfo info = HttpTool.getRequestInfo(request);
        if(ObjectUtils.isNotEmpty(info)){
            logEvent.setBrowser(info.getBrowser());
            logEvent.setIp(info.getIp4());
            logEvent.setOs(info.getOs());
            logEvent.setMobile(info.getMobile());
        }

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        logEvent.setFunction(method.getName());
        logEvent.setPack(joinPoint.getTarget().getClass().getName());

        //获取方法输入参数
        Map<String, String> rtnMap = HttpTool.getRequestParam(request.getParameterMap());
        logEvent.setParam(JSON.toJSONString(rtnMap));

        //获取注解中的value值
        Log logMsg = method.getAnnotation(Log.class);
        logEvent.setLogDesc(logMsg.desc());
        logEvent.setLogType(logMsg.type());
        logEvent.setLogValue(logMsg.value());
        logEvent.setLogRecord(logMsg.record());
        logEvent.setLogTime(DateUtil.now());

        startTimeThreadLocal.set(System.currentTimeMillis());
        //将日志对象存储到ThreadLocal对象下，方便对象传递
        logBasicsThreadLocal.set(logEvent);
        isPersistentThreadLocal.set(logMsg.persistent());
    }


    @AfterReturning(pointcut ="start()",returning = "object")
    public void afterReturning(Object object) {
        if (logBasicsThreadLocal.get()!= null) {
            LogEvent logEvent =logBasicsThreadLocal.get();
            if(isPersistentThreadLocal.get() != null){
                try {
                    logEvent.setType("INFO");
                    logHandle.processLog(logEvent,isPersistentThreadLocal.get());
                }catch (Exception e){
                    log.error(e.toString());
                }
            }
        }
        startTimeThreadLocal.remove();
        logBasicsThreadLocal.remove();
    }


    @AfterThrowing(pointcut = "start()",throwing = "throwable")
    public void afterThrowing(Throwable throwable) {
        if (logBasicsThreadLocal.get()!= null) {
            LogEvent logEvent =logBasicsThreadLocal.get();
            logEvent.setException(throwable.getClass().getName());

            if(isPersistentThreadLocal.get() != null){
                try {
                    logEvent.setType("ERROR");
                    logHandle.processLog(logEvent,isPersistentThreadLocal.get());
                }catch (Exception e){
                    log.error(e.toString());
                }
            }
            startTimeThreadLocal.remove();
            logBasicsThreadLocal.remove();
        }
    }















}
