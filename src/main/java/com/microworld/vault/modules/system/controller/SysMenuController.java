package com.microworld.vault.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bird.common.annotation.Log;
import com.bird.common.exception.AssertException;
import com.microworld.vault.modules.system.entity.SysMenu;
import com.microworld.vault.modules.system.request.MenuCreateRequest;
import com.microworld.vault.modules.system.request.MenuListRequest;
import com.microworld.vault.modules.system.request.MenuPageRequest;
import com.microworld.vault.modules.system.request.MenuUpdateRequest;
import com.microworld.vault.modules.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author birdBro
 * @since 2022-04-14
 */
@Api(value = "菜单", tags = "A-00_Menu")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/sys/menu")
public class SysMenuController {

    private final ISysMenuService iMenuService;


    @Log(value = "添加菜单", type = "POST")
    @PostMapping("/create")
    @ApiOperation(value = "添加菜单")
    public Integer create(@Validated @RequestBody MenuCreateRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iMenuService.create(request);
    }


    @Log(value = "修改菜单", type = "POST")
    @PostMapping("/update")
    @ApiOperation(value = "修改菜单")
    public Boolean update(@Validated @RequestBody MenuUpdateRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iMenuService.update(request);
    }


    @Log(value = "删除菜单", type = "DELETE")
    @DeleteMapping(path = "/{mid}")
    @ApiOperation(value = "删除菜单 ")
    @ApiImplicitParam(name = "mid", value = "菜单ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public Boolean remove(@PathVariable(value = "mid") Integer mid) {
        return iMenuService.removeById(mid);
    }


    @GetMapping(path = "/{mid}")
    @ApiOperation(value = "查询菜单-entity")
    @ApiImplicitParam(name = "mid", value = "菜单ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public SysMenu queryEntity(@PathVariable(value = "mid") Integer mid){
        return iMenuService.getById(mid);
    }


    @PostMapping("/list")
    @ApiOperation(value = "查询菜单-list")
    public List<SysMenu> queryList(@RequestBody MenuListRequest request){
        return iMenuService.queryList(request);
    }


    @PostMapping("/page")
    @ApiOperation(value = "查询菜单-page")
    public IPage<SysMenu> queryPage(@Validated @RequestBody MenuPageRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iMenuService.queryPage(request);
    }


    @PostMapping("/tree")
    @ApiOperation(value = "查询菜单-tree")
    public List<SysMenu> queryTree(@RequestBody MenuListRequest request){
        return iMenuService.queryTree(request);
    }



}

