package com.microworld.vault.modules.system.service.impl;

import com.bird.common.entity.CookieVariable;
import com.bird.common.entity.HttpRequestInfo;
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
import com.microworld.vault.modules.log.service.ILogSignService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    private final ISysUserService iUserService;

    private final IUserSessionService iUserSessionService;

    private final ILogSignService iLogSignService;

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
        if(iUserService.save(user)){
            return user.getUid();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0100,"sign up is fail ！");
        }
    }


    @Override
    public UserInfoResponse signIn(SignInRequest sign, HttpServletRequest request, HttpServletResponse response) {

        UserInfoResponse userInfoResponse = UserInfoResponse.builder().build();

        //获取账号信息
        SysUser user = iUserService.query(sign.getAccount());

        /**
         * 校验账号
         */
        if(ObjectUtils.isEmpty(user)){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0201, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }
        if(!AuthTool.aesCheckPassword(sign.getAccount(), sign.getPassword(), user.getPassword(), redisUtil.get(Constants.RAS_PRIVATE_KEY))){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0210, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }
        if(user.getEnable().equals(EnableEnum.FALSE.getKey())){
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0322, ErrorCodeEnum.BUSINESS_ERROR_A0200.getMsg());
        }

        /**
         * 登录成功
         */
        ObjectTool.copyPropertiesIgnoreNull(user, userInfoResponse);


        //写 cookie

        //写 redis

        //写db session
        HttpRequestInfo httpRequestInfo = HttpTool.getRequestInfo(request);
        iUserSessionService.create(user, httpRequestInfo);
        //获取权限

        //日志
        iLogSignService.createIn(user.getUid(), httpRequestInfo);


        return userInfoResponse;
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
        //日志
        HttpRequestInfo httpRequestInfo = HttpTool.getRequestInfo(request);
        iLogSignService.createOut(sign.getUid(), httpRequestInfo);
        return true;
    }

}
