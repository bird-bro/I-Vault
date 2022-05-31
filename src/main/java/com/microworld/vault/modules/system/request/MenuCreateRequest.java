package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-15 9:55
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuCreateRequest {

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
     * 备注
     */
    private String remark;
}
