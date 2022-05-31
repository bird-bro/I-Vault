package com.microworld.vault.modules.system.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author bird
 * @date 2022-4-15 10:38
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuListRequest {

    /**
     * 标题
     */
    private String title;

    /**
     * 子系统
     */
    private Integer app;

    /**
     * 父节点
     */
    private Integer parentId;


    /**
     * 角色菜单
     */
    @JsonIgnore
    private List<Integer> mids;
}
