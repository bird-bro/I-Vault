package com.microworld.vault.modules.system.request;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author birdbro
 * @date 16:11 2022-4-25
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgUpdateRequest extends OrgCreateRequest{


    /**
     * 主键
     */
    @NotNull
    private Integer oid;

    /**
     * 启用 1 禁用 0
     */
    private Integer enable;

}
