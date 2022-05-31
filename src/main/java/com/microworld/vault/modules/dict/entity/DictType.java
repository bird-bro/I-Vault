package com.microworld.vault.modules.dict.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典类型
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DictType extends Model<DictType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 启用 1(def)  禁用 0 
     */
    private Integer enable;

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
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Integer updateBy;

    /**
     * 更新时间
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
        return this.id;
    }

}
