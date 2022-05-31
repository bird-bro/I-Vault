package com.microworld.vault.modules.dict.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-12 16:34
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictTypeUpdateRequest extends DictTypeCreateRequest{

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 启用 1(def)  禁用 0
     */
    private Integer enable;

}
