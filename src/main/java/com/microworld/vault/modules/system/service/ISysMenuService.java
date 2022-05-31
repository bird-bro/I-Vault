package com.microworld.vault.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.system.entity.SysMenu;
import com.microworld.vault.modules.system.request.MenuCreateRequest;
import com.microworld.vault.modules.system.request.MenuListRequest;
import com.microworld.vault.modules.system.request.MenuPageRequest;
import com.microworld.vault.modules.system.request.MenuUpdateRequest;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-14
 */
public interface ISysMenuService extends IService<SysMenu> {



    /**
     * 添加菜单
     * @author: bird
     * @date: 2022-4-15 13:51
     * @param:
     * @return:
     **/
    Integer create (MenuCreateRequest request);


    /**
     * 修改菜单
     * @author: bird
     * @date: 2022-4-15 13:51
     * @param:
     * @return:
     **/
    Boolean update (MenuUpdateRequest request);

    /**
     * 查询菜单 list
     * @author: bird
     * @date: 2022-4-15 13:51
     * @param: MenuListRequest
     * @return:
     **/
    List<SysMenu> queryList(MenuListRequest request);


    /**
     * 查询菜单树
     * @author: bird
     * @date: 2022-4-15 13:50
     * @param:
     * @return:
     **/
    List<SysMenu> queryTree(MenuListRequest request);


    /**
     * 查询菜单 page
     * @author: bird
     * @date: 2022-4-15 13:56
     * @param:
     * @return:
     **/
    IPage<SysMenu> queryPage(MenuPageRequest request);


}
