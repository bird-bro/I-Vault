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
 * 字典数据
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DictData extends Model<DictData> {

    private static final long serialVersionUID = 1L;

    /**
     * PK
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 启用 1(def)  禁用 0 
     */
    private Integer enable;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 编码
     */
    private Integer code;

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
    private Integer updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
