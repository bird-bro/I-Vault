package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-15 9:56
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuUpdateRequest extends MenuCreateRequest{


    /**
     * 主键:1001
     */
    private Integer mid;



}
