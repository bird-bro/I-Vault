package com.microworld.vault.enums;

import lombok.Getter;
import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * 字典分类-枚举
 * @author bird
 * @date 2022-4-14 11:17
 **/
@Getter
public enum DictClsEnum implements BaseEnum{

    /**
     * 目录
     */
    CLS_PUBLIC (1,"公共"),

    /**
     * 菜单
     */
    CLS_STATUS (2,"状态"),

    /**
     * 按钮
     */
    CLS_TYPE (3,"分类"),

    /**
     * 接口
     */
    CLS_OTHER (9,"其它"),

    ;


    private final Integer key;

    private final String value;


    DictClsEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<DictClsEnum> getEnums(){
        List<DictClsEnum> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }
}
