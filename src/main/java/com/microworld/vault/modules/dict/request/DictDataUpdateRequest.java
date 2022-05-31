package com.microworld.vault.modules.dict.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-13 9:41
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDataUpdateRequest extends DictDataCreateRequest{

    /**
     * 主键
     */
    private Integer id;

    /**
     * 启用 1(def)  禁用 0
     */
    private Integer enable;

}
