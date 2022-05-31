package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册
 * @author bird
 * @date 2022-4-15 15:48
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SignUpRequest {


    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

}
