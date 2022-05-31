package com.microworld.vault.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.tools.ObjectTool;
import com.microworld.vault.modules.system.entity.SysMenu;
import com.microworld.vault.modules.system.mapper.SysMenuMapper;
import com.microworld.vault.modules.system.request.MenuCreateRequest;
import com.microworld.vault.modules.system.request.MenuListRequest;
import com.microworld.vault.modules.system.request.MenuPageRequest;
import com.microworld.vault.modules.system.request.MenuUpdateRequest;
import com.microworld.vault.modules.system.service.ISysMenuService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-14
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public Integer create(MenuCreateRequest request) {
        SysMenu menu = SysMenu.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request, menu);
        if(this.save(menu)){
            return menu.getMid();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_B0001,"create menu is fail ！");
        }
    }


    @Override
    public Boolean update(MenuUpdateRequest request) {
        SysMenu menu = SysMenu.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request, menu);
        return this.updateById(menu);
    }


    @Override
    public List<SysMenu> queryList(MenuListRequest request) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ObjectUtils.isNotEmpty(request.getApp()), SysMenu::getApp, request.getApp())
                .eq(ObjectUtils.isNotEmpty(request.getParentId()), SysMenu::getParentId, request.getParentId())
                .like(StringUtils.isNotBlank(request.getTitle()), SysMenu::getTitle, request.getTitle())
                .in(CollectionUtil.isNotEmpty(request.getMids()), SysMenu::getMid, request.getMids())
                .orderByAsc(SysMenu::getSort);
        return this.list(wrapper);
    }


    @Override
    public List<SysMenu> queryTree(MenuListRequest request) {

        List<SysMenu> menuList = queryList(request);
       if(CollectionUtil.isEmpty(menuList)){
           return null;
       }
       return menuList.stream()
               .filter(parent -> parent.getParentId().equals(0))
               .peek(child -> child.setChildren(getChildren(menuList, child.getMid())))
               .collect(Collectors.toList());
    }


    private List<SysMenu> getChildren (List<SysMenu> menus, Integer parentId){
        return menus.stream()
                // 过滤父节点
                .filter(parent -> parent.getParentId().equals(parentId))
                // 将父节点children递归赋值成为子节点
                .peek(child -> child.setChildren(getChildren(menus, child.getMid())))
                .collect(Collectors.toList());
    }


    @Override
    public IPage<SysMenu> queryPage(MenuPageRequest request) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ObjectUtils.isNotEmpty(request.getApp()), SysMenu::getApp, request.getApp())
                .eq(ObjectUtils.isNotEmpty(request.getParentId()), SysMenu::getParentId, request.getParentId())
                .like(StringUtils.isNotBlank(request.getTitle()), SysMenu::getTitle, request.getTitle())
                .orderByAsc(SysMenu::getSort);
        Page<SysMenu> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, wrapper);
    }


}
