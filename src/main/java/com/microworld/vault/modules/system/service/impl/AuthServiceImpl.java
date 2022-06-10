package com.microworld.vault.modules.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bird.common.entity.CookieVariable;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.redis.RedisUtil;
import com.bird.common.tools.CharsTool;
import com.bird.common.tools.HttpTool;
import com.bird.common.tools.ObjectTool;
import com.microworld.common.tools.AuthTool;
import com.microworld.common.Constants;
import com.microworld.vault.enums.EnableEnum;
import com.microworld.vault.modules.dict.service.IAccessoryService;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.request.SignInRequest;
import com.microworld.vault.modules.system.request.SignOutRequest;
import com.microworld.vault.modules.system.request.SignUpRequest;
import com.microworld.vault.modules.system.response.UserInfoResponse;
import com.microworld.vault.modules.system.service.IAuthService;
import com.microworld.vault.modules.system.service.ISysUserService;
import com.microworld.vault.modules.system.service.IUserSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.time.Duration;

/**
 * 默认
 * @author bird
 * @date 2022-4-19 15:22
 **/
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Value("${spring.profiles.active}")
    private String env;
    @Value("${auth.default.domain}")
    private String domain;
    @Value("${auth.default.header}")
    private String header;
    @Value("${auth.token-timeout}")
    private Duration tokenTimeout;
    @Value("${auth.session-timeout}")
    private Duration sessionTimeout;

    private static int cookie_expire = 24 * 60 * 60;



    private final ISysUserService iUserService;

    private final IUserSessionService iUserSessionService;

    private final IAccessoryService iAccessoryService;

    private final RedisUtil redisUtil;



    @Override
    public Integer signUp(SignUpRequest request) {
        SysUser user = SysUser.builder()
                .enable(EnableEnum.TRUE.getKey())
                .initial(CharsTool.oneChars(request.getName()))
                .build();
        ObjectTool.copyPropertiesIgnoreNull(request, user);
        //生成默认头像
        if(StringUtils.isBlank(user.getHeadIcon())){
            user.setHeadIcon(iAccessoryService.headIcon(StringUtils.substring(request.getName(),0,1)));
        }
        try {
            user.setPassword(AuthTool.aesPassword(request.getAccount(),request.getPassword()));
        }catch (Exception e){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0100,"sign up is fail ！（Password encryption failed）");
        }

        if(iUserService.save(user)){
            return user.getUid();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0100,"sign up is fail ！");
        }
    }


    @Override
    public UserInfoResponse signIn(SignInRequest sign, HttpServletRequest request, HttpServletResponse response) {

        UserInfoResponse userInfo = UserInfoResponse.builder().build();

        //获取账号信息
        SysUser user = iUserService.query(sign.getAccount());

        /**
         * 校验账号
         */
        if(ObjectUtils.isEmpty(user)){
            //账户不存在
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0201, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }
        if(!AuthTool.aesCheckPassword(sign.getAccount(), sign.getPassword(), user.getPassword())){
            //密码错误
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0210, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }
        if(user.getEnable().equals(EnableEnum.FALSE.getKey())){
            //账号禁用
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0322, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }

        /*
         * 登录成功
         **/
        ObjectTool.copyPropertiesIgnoreNull(user, userInfo);

        //写token
        userInfo.setToken(getToken(userInfo.getAccount(), userInfo.getPassword()));
        //写cookies
        setCookie(userInfo, response);
        //写redis
        redisUtil.set(Constants.REDIS_USER_INFO+userInfo.getAccount(),userInfo,tokenTimeout.toMillis());
        //写session
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.setAttribute("uid",user.getUid());
        //写session db
        iUserSessionService.create(request);
        //更新登录记录
        iUserService.renewSign(userInfo.getUid(), HttpTool.getIp4(request), userInfo.getInNo());

        /*
        *获取权限
        **/
        //菜单权限




        return userInfo;
    }


    @Override
    public Boolean signOut(SignOutRequest sign, HttpServletRequest request, HttpServletResponse response) {
        CookieVariable variable = new CookieVariable();
        variable.setDomain(domain);
        variable.setHeader(header);
        variable.setEnv(env);

        Cookie[] cookies = request.getCookies();
        HttpTool.removeCookie(cookies, response, variable);

        //删 ridis
        redisUtil.delete(Constants.REDIS_USER_INFO + sign.getAccount());
        //删db session
        iUserSessionService.remove(sign.getUid());

        return true;
    }



    /**
     * new token
     * @author:bird
     **/
    private String getToken(String code, String password){
        String token = "";
        token = JWT.create().withAudience(code).sign(Algorithm.HMAC256(password));
        return token;
    }

    /**
     * set cookie
     * @author:bird
     **/
    private void setCookie(UserInfoResponse infoUserResponse, HttpServletResponse response){
        String userInfo = "";
        try {
            userInfo = URLEncoder.encode(JSON.toJSONString(userInfo),"UTF-8");
            //写cookie
            String[] domainArr = domain.split(";");
            for (int i = 0; i < domainArr.length; i++) {
                Cookie cookie = new Cookie(header + "-" + env, infoUserResponse.getToken());
                cookie.setMaxAge(cookie_expire);
                cookie.setDomain(domainArr[i]);
                cookie.setPath("/");
                response.addCookie(cookie);
                cookie = new Cookie("user_info-" + env, userInfo);
                //cookie = new Cookie("USER-UID", String.valueOf(infoUserResponse.getUid()));
                cookie.setMaxAge(cookie_expire);
                cookie.setDomain(domainArr[i]);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }catch (Exception e){
            log.error("-- cookie写入失败",e);
        }
    }

}
