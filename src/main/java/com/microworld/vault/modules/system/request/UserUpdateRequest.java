package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-15 15:38
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateRequest extends UserCreateRequest{

    /**
     * 主键
     */
    private Integer uid;

    /**
     * 启用 1 禁用 0
     */
    private Integer enable;




}
