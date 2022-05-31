package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author birdBro
 * @since 2022-04-14
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键:1001
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 标题
     */
    private String title;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 组件
     */
    private String component;

    /**
     * 类型: M目录 C菜单 F按钮 I 接口
     */
    private String type;

    /**
     * 启用 1(def) 禁用 0 
     */
    private Integer enable;

    /**
     * 显示 1 不显示 0(def)
     */
    private Boolean visible;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 子系统
     */
    private Integer app;

    /**
     * 面包屑
     */
    private String breadcrumb;

    /**
     * 动作
     */
    private String action;

    /**
     * 缓存 （0否 1是） def0
     */
    private Boolean isCache;

    /**
     * 外连接 （0否 1是）def0
     */
    private Boolean isFrame;

    /**
     * 权限标识
     */
    private String perms;


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
     * 备注
     */
    private String remark;



    @Override
    protected Serializable pkVal() {
        return this.mid;
    }



    @TableField(exist = false)
    @ApiModelProperty(value = "子节点")
    private List<SysMenu> children;

}
