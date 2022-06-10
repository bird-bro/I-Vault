package com.microworld.vault.modules.system.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author bird
 * @date 2022-4-20 10:30
 **/
@Data
public class SignOutRequest {

    @NotBlank(message = "账号/手机号不能为空")
    private String account;

    @NotNull(message = "用户ID")
    private Integer uid;

}
