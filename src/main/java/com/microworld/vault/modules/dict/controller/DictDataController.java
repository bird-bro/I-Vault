package com.microworld.vault.modules.dict.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bird.common.annotation.Log;
import com.bird.common.exception.AssertException;
import com.microworld.vault.modules.dict.entity.DictData;
import com.microworld.vault.modules.dict.request.DictDataCreateRequest;
import com.microworld.vault.modules.dict.request.DictDataPageRequest;
import com.microworld.vault.modules.dict.request.DictDataUpdateRequest;
import com.microworld.vault.modules.dict.response.DictDataResponse;
import com.microworld.vault.modules.dict.service.IDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bird
 * @date 2022-4-13 9:33
 **/
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "Z-02_DictData")
@RestController
@RequestMapping("/api/dict/data")
public class DictDataController {


    private final IDictDataService iDataService;


    @Log(value = "添加字典数据", type = "POST")
    @PostMapping("/create")
    @ApiOperation(value = "添加字典数据")
    public Integer create(@Validated @RequestBody DictDataCreateRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iDataService.create(request);
    }

    @Log(value = "修改字典数据", type = "POST")
    @PostMapping("/update")
    @ApiOperation(value = "修改字典数据")
    public Boolean update(@Validated @RequestBody DictDataUpdateRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iDataService.update(request);
    }


    @Log(value = "删除字典数据", type = "DELETE")
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "删除字典数据 ")
    @ApiImplicitParam(name = "id", value = "字典数据ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public Boolean remove(@PathVariable(value = "id") Integer id) {
        return iDataService.removeById(id);
    }


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "查询字典数据-entity")
    @ApiImplicitParam(name = "id", value = "字典数据ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public DictData queryEntity(@PathVariable(value = "id") Integer id){
        return iDataService.getById(id);
    }


    @GetMapping("/list/type/{typeId}")
    @ApiOperation(value = "查询字典数据-list")
    @ApiImplicitParam(name = "typeId", value = "字典类型ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public List<DictDataResponse> queryList(@PathVariable(value = "typeId") Integer typeId) {
        return iDataService.queryList(typeId);
    }



    @PostMapping("/list/map")
    @ApiOperation(value = "查询字典数据-list map")
    public Map<Integer, List<DictDataResponse>> queryListMap(@RequestBody List<Integer> typeIds) {
        return iDataService.queryListMap(typeIds);
    }


    @PostMapping("/page")
    @ApiOperation(value = "查询字典数据-page")
    public IPage<DictData> queryPage(@Validated @RequestBody DictDataPageRequest request, BindingResult bindingResult) {
        AssertException.validated(bindingResult);
        return iDataService.queryPage(request);
    }


    @GetMapping("/exist/value/{value}")
    @ApiOperation(value = "按值检查字典数据是否存在")
    @ApiImplicitParam(name = "value", value = "字典数据值", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByValue(@PathVariable(value = "value") String value) {
        return iDataService.exist(value);
    }






}
