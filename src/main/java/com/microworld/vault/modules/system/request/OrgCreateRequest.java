package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author birdbro
 * @date 16:10 2022-4-25
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgCreateRequest {

    /**
     * 名称
     */
    @NotBlank
    private String name;

    /**
     * 简称
     */
    private String nameShort;

    /**
     * 社会代码
     */
    private String cods;

    /**
     * 图标
     */
    private String logo;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 官网
     */
    private String website;

    /**
     * 地址
     */
    private String addr;

    /**
     * 简介
     */
    private String intro;

    /**
     * 地区
     */
    private String areaValue;

    /**
     * 地区ID
     */
    private Integer area;

    /**
     * 类型
     */
    private String typeValue;

    /**
     * 类型ID
     */
    private Integer type;

    /**
     * 等级
     */
    private String levelValue;

    /**
     * 等级ID
     */
    private Integer level;

    /**
     * 伦理委员会
     */
    private Boolean isEc;


}
