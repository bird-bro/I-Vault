package com.microworld.vault.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 启用状态
 * @author birdbro
 * @date 14:11 2022-4-25
 **/
@Getter
public enum EnableEnum implements BaseEnum{


    /**
     * 启用
     */
    TRUE (1,"启用"),

    /**
     * 禁用
     */
    FALSE (0,"禁用"),

    /**
     * 延迟
     */
    DELAY (2,"等待"),
    ;


    private int key;

    private String value;



    EnableEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<EnableEnum> getAll(){
        List<EnableEnum> list = new ArrayList<>();
        list.addAll(Arrays.asList(values()));
        return list;
    }
}
