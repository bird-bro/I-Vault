package com.microworld.vault.modules.system.service;

import com.microworld.vault.modules.system.request.SignInRequest;
import com.microworld.vault.modules.system.request.SignOutRequest;
import com.microworld.vault.modules.system.request.SignUpRequest;
import com.microworld.vault.modules.system.response.UserInfoResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author bird
 * @date 2022-4-19 15:22
 **/
public interface IAuthService {


    /**
    * 注册
    * @author: birdbro
    * @date: 2022-4-29 16:16
    * @param:
    * @return:
    **/
    Integer signUp(SignUpRequest request);


    /**
     * 登录
     * @author: bird
     * @date: 2022-4-20 10:19
     * @param:
     * @return:
     **/
    UserInfoResponse signIn (SignInRequest sign, HttpServletRequest request, HttpServletResponse response);


    /**
     * 登出
     * @author: bird
     * @date: 2022-4-19 15:28
     * @param:
     * @return:
     **/
    Boolean signOut (String account);


}
