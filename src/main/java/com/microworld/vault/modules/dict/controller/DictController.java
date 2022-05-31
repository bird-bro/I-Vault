package com.microworld.vault.modules.dict.controller;


import com.microworld.vault.modules.dict.entity.DictCity;
import com.microworld.vault.modules.dict.service.IDictCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 城市行政区 前端控制器
 * </p>
 *
 * @author birdBro
 * @since 2022-04-11
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(value = "数据字典", tags = "Z-00_Dict")
@RestController
@RequestMapping("/api/dict")
public class DictController {


    private final IDictCityService iCityService;




    @GetMapping("/city/tree")
    @ApiOperation(value = "查询城市树")
    public List<DictCity> listCityToTree (){
        return iCityService.queryTree();
    }


    @GetMapping("/city/province")
    @ApiOperation(value = "查询省级list")
    public List<DictCity> listCity (){
        return iCityService.queryListToProvince();
    }


    @GetMapping("/city/{id}")
    @ApiOperation(value = "查询市/县级list")
    @ApiImplicitParam(name = "id",value = "省/市ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public List<DictCity> listCity (@PathVariable("id") Integer id){
        return iCityService.queryList(id);
    }



}

