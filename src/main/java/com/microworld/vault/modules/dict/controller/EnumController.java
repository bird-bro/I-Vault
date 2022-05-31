package com.microworld.vault.modules.dict.controller;

import com.microworld.vault.enums.DictClsEnum;
import com.microworld.vault.enums.EnumsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 枚举
 * @author bird
 * @date 2022-4-14 11:14
 **/
@Api(value = "枚举", tags = "Z-03_Enum")
@RestController
@RequestMapping("/api/enum")
public class EnumController {


    @GetMapping("/all")
    @ApiOperation(value = "获取全部枚举")
    public EnumsResponse getAll(){
        return EnumsResponse.builder()
                .clsEnums(DictClsEnum.getEnums())
                .build();
    }

    @GetMapping("/dict/cls")
    @ApiOperation(value = "获取字典分类枚举")
    public List<DictClsEnum> listDictCls(){
        return DictClsEnum.getEnums();
    }
}
