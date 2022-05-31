package com.microworld.vault.modules.system.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author bird
 * @date 2022-4-20 9:22
 **/
@Data
public class SignInRequest {

    @NotBlank(message = "账号/手机号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

}
