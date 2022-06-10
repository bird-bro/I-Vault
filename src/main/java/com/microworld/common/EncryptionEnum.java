package com.microworld.common;

import lombok.Getter;

/**
 * @author birdbro
 * @date 15:14 2022-4-28
 **/
@Getter
public enum EncryptionEnum {

    /**
     * 加密方式
     */
    ALGORITHMNAME("md5"),
    /**
     *表示加密系数
     */
    HASHITERATIONS("2"),

    /**
     *表示加密所用盐值
     */
    SALT("pixel");



    private String value;

    EncryptionEnum(String value) {
        this.value = value;
    }

}
