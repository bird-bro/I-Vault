package com.microworld.vault.modules.dict.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bird.common.annotation.Log;
import com.bird.common.exception.AssertException;
import com.microworld.vault.modules.dict.entity.DictType;
import com.microworld.vault.modules.dict.request.DictTypeCreateRequest;
import com.microworld.vault.modules.dict.request.DictTypePageRequest;
import com.microworld.vault.modules.dict.request.DictTypeUpdateRequest;
import com.microworld.vault.modules.dict.service.IDictTypeService;
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
 * @author bird
 * @date 2022-4-12 16:56
 **/
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "Z-01_DictType")
@RestController
@RequestMapping("/api/dict/type")
public class DictTypeController {


    private final IDictTypeService iTypeService;


    @Log(value = "添加字典类型", type = "POST")
    @PostMapping("/create")
    @ApiOperation(value = "添加字典类型")
    public Integer create(@Validated @RequestBody DictTypeCreateRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iTypeService.create(request);
    }


    @Log(value = "修改字典类型", type = "POST")
    @PostMapping("/update")
    @ApiOperation(value = "修改字典类型")
    public Boolean update(@Validated @RequestBody DictTypeUpdateRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iTypeService.update(request);
    }


    @Log(value = "删除字典类型", type = "DELETE")
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "删除字典类型")
    @ApiImplicitParam(name = "id", value = "字典目录ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public Boolean remove(@PathVariable(value = "id") Integer id) {
        return iTypeService.removeById(id);
    }


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "查询字典类型-entity ")
    @ApiImplicitParam(name = "id", value = "字典目录ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public DictType queryEntity(@PathVariable(value = "id") Integer id){
        return iTypeService.getById(id);
    }


    @GetMapping("/list")
    @ApiOperation(value = "查询字典类型-list")
    public List<DictType> queryList() {
        return iTypeService.queryList();
    }


    @PostMapping("/page")
    @ApiOperation(value = "查询字典类型-page")
    public IPage<DictType> queryPage(@Validated @RequestBody DictTypePageRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iTypeService.queryPage(request);
    }


    @GetMapping("/exist/name/{name}")
    @ApiOperation(value = "按名称检查字典类型是否存在")
    @ApiImplicitParam(name = "name", value = "字典名", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByName(@PathVariable(value = "name") String name) {
        return iTypeService.exist(name);
    }
}
