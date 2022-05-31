package com.microworld.vault.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bird.common.annotation.Log;
import com.bird.common.exception.AssertException;
import com.microworld.vault.modules.system.entity.SysOrg;
import com.microworld.vault.modules.system.request.OrgCreateRequest;
import com.microworld.vault.modules.system.request.OrgPageRequest;
import com.microworld.vault.modules.system.request.OrgUpdateRequest;
import com.microworld.vault.modules.system.service.ISysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "A-03_Org")
@RestController
@RequestMapping("/api/sys/org")
public class SysOrgController {


    private final ISysOrgService iOrgService;



    @Log(value = "新增机构", type = "POST")
    @PostMapping("/create")
    @ApiOperation(value = "新增机构")
    public Integer create(@Validated @RequestBody OrgCreateRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iOrgService.create(request);
    }


    @Log(value = "修改机构", type = "POST")
    @PostMapping("/update")
    @ApiOperation(value = "修改机构")
    public Boolean update(@Validated @RequestBody OrgUpdateRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iOrgService.update(request);
    }


    @Log(value = "删除机构", type = "DELETE")
    @DeleteMapping(path = "/{oid}")
    @ApiOperation(value = "删除机构")
    @ApiImplicitParam(name = "oid", value = "机构ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public Boolean remove(@PathVariable(value = "oid") Integer oid){
        return iOrgService.removeById(oid);
    }


    @GetMapping(path = "/{oid}")
    @ApiOperation(value = "查询机构-entity ")
    @ApiImplicitParam(name = "oid", value = "机构ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public SysOrg query(@PathVariable(value = "oid") Integer oid){ return iOrgService.getById(oid); }


    @PostMapping("/page")
    @ApiOperation(value = "查询机构-page")
    public IPage<SysOrg> queryPage(@Validated @RequestBody OrgPageRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iOrgService.queryPage(request);
    }





}

