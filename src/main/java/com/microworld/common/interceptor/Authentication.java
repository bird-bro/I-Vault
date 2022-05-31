package com.microworld.common.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bird.common.annotation.PassToken;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.redis.RedisUtil;
import com.bird.common.tools.HttpTool;
import com.microworld.common.Constants;
import com.microworld.vault.modules.system.response.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 身份认证认证拦截
 * @author birdbro
 * @date 11:17 2022-4-28
 **/
@Slf4j
public class Authentication implements HandlerInterceptor {

    @Value("${auth.default.header}")
    private String header;
    @Value("${spring.profiles.active}")
    private String env;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(header+"-"+env);
        //检查Token是否为空并解析Token
        String account = HttpTool.checkToken(token);
        //获取redis中缓存token
        String redisInfo = redisUtil.get(Constants.REDIS_USER_INFO +account);

        //token失效
        if(StringUtils.isBlank(redisInfo)){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0230, "token invalid: "+account);
        }
        UserInfoResponse userInfoResponse = JSONObject.parseObject(redisInfo, UserInfoResponse.class);
        if(ObjectUtils.isEmpty(userInfoResponse)){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0600, "redis user info is null!");
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfoResponse.getPassword())).build();

        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0320, "token check fail！");
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
