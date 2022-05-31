package com.microworld.vault.modules.system.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author bird
 * @date 2022-4-15 15:37
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCreateRequest {


    /**
     * 账号
     */
    @NotBlank(message = "用户账号不能为空！")
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    @NotBlank(message = "用户姓名不能为空！")
    private String name;

    /**
     * 头像
     */
    private String headIcon;

    /**
     * 性别 0女 1男
     */
    private Integer gender;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 籍贯
     */
    private String birthplace;

    /**
     * 地址
     */
    private String address;

    /**
     * 签名
     */
    private String signature;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 专业
     */
    private Integer major;

    /**
     * 职称
     */
    private Integer title;

    /**
     * 职称编码
     */
    private String titleValue;

    /**
     * 学历
     */
    private Integer education;

    /**
     * 职务
     */
    private Integer job;

    /**
     * 参加工作时间
     */
    private Date joinIn;

    /**
     * 擅长
     */
    private String speciality;

    /**
     * 个人简介
     */
    private String intro;
}
