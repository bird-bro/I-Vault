package com.microworld.vault.modules.dict.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-12 16:33
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictTypeCreateRequest {

    /**
     * 索引名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分类
     */
    private Integer cls;

}
