package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author birdBro
 * @since 2022-04-15
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 启用 1 禁用 0 
     */
    private Integer enable;

    /**
     * 账号
     */
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

    /**
     * 首字母
     */
    private String initial;

    /**
     * 删除 1 正常 0(def)
     */
    @TableLogic
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Integer updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

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


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
