package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrg extends Model<SysOrg> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 启用 1 禁用 0 
     */
    private Integer enable;

    /**
     * 名称
     */
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
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

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

    /**
     * 删除 1 正常 0(def)
     */
    @TableLogic
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Integer updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

    /**
     * 首字母
     */
    private String initial;


    @Override
    protected Serializable pkVal() {
        return this.oid;
    }

}
