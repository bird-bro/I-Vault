package com.microworld.vault.modules.system.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

/**
 * @author bird
 * @date 2022-4-19 14:45
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoResponse {

    /**
     * 主键
     */
    private Integer uid;

    /**
     * 账号
     */
    private String account;




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
     * 生日
     */
    private Date birthday;

    /**
     * 职称
     */
    private Integer title;

    /**
     * 职务
     */
    private Integer job;

    /**
     * 首字母
     */
    private String initial;

    /**
     * 最近登录时间
     */
    private Date inTime;

    /**
     * 累计登录次数
     */
    private Integer inNo;

    /**
     * 最近登录IP
     */
    private String inIp;




    private String password;

    private String token;
}
