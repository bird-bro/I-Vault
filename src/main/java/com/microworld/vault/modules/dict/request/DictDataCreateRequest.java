package com.microworld.vault.modules.dict.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-13 9:40
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDataCreateRequest {

    /**
     * 类型id
     */
    private Integer typeId;


    /**
     * 值
     */
    private String value;

    /**
     * 标签
     */
    private String label;

    /**
     * 备用值
     */
    private String reserve;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 颜色
     */
    private String color;

    /**
     * 样式
     */
    private String css;

    /**
     * 备注
     */
    private String remark;

}
