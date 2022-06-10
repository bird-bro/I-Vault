package com.microworld.common.aspect;

import cn.hutool.core.date.DateUtil;
import com.bird.common.annotation.Log;
import com.bird.common.aspect.LogHandle;
import com.bird.common.tools.HttpTool;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.common.Constants;
import com.microworld.vault.modules.system.entity.LogInfo;
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


/**
 * @author birdbro
 * @date 13:54 2022-4-28
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Value("${spring.application.name}")
    private String app;


    private ThreadLocal<Long> startTimeThreadLocal=new ThreadLocal<>();
    private ThreadLocal<LogInfo> logBasicsThreadLocal=new ThreadLocal<>();


    @Autowired
    private LogHandle<LogInfo> logHandle;

    @Pointcut("@annotation(com.bird.common.annotation.Log)")
    public void start(){
    }


    @Before("start()")
    public void before(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttr.getRequest();

        LogInfo logInfo = new LogInfo();
        logInfo.setApp(app);
        logInfo.setUid((String)request.getHeader(Constants.HEAD_USER_UID));
        logInfo.setAccount((String) request.getHeader(Constants.HEAD_USER_ACCOUNT));
        logInfo.setSession(requestAttr.getSessionId());
        logInfo.setUrl(request.getRequestURI().toString());
        logInfo.setMethod(request.getMethod());


        HttpRequestInfo info = HttpTool.getRequestInfo(request);
        if(ObjectUtils.isNotEmpty(info)){
            logInfo.setBrowser(info.getBrowser());
            logInfo.setIp(info.getIp4());
            logInfo.setOs(info.getOs());
            logInfo.setMobile(info.getMobile());
            logInfo.setEngine(info.getEngine());
        }

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        logInfo.setFunction(method.getName());
        logInfo.setPack(joinPoint.getTarget().getClass().getName());

        //获取注解中的value值
        Log logMsg = method.getAnnotation(Log.class);
        logInfo.setLogDesc(logMsg.desc());
        logInfo.setLogType(logMsg.type());
        logInfo.setLogValue(logMsg.value());
        logInfo.setLogTime(DateUtil.now());

        startTimeThreadLocal.set(System.currentTimeMillis());
        //将日志对象存储到ThreadLocal对象下，方便对象传递
        logBasicsThreadLocal.set(logInfo);
    }



    @AfterReturning(pointcut ="start()",returning = "object")
    public void afterReturning(Object object) {
        if (logBasicsThreadLocal.get()!= null) {
            LogInfo loginfo =logBasicsThreadLocal.get();
            try {
                loginfo.setType("INFO");
                logHandle.persistenceLog(loginfo);
            }catch (Exception e){
                log.error(e.toString());
            }
        }
        startTimeThreadLocal.remove();
        logBasicsThreadLocal.remove();
    }



    @AfterThrowing(pointcut = "start()",throwing = "throwable")
    public void afterThrowing(Throwable throwable) {
        if (logBasicsThreadLocal.get()!= null) {
            LogInfo loginfo =logBasicsThreadLocal.get();
            loginfo.setException(throwable.getClass().getName());


                try {
                    loginfo.setType("ERROR");
                    logHandle.persistenceLog(loginfo);
                }catch (Exception e){
                    log.error(e.toString());
                }

            startTimeThreadLocal.remove();
            logBasicsThreadLocal.remove();
        }
    }















}
