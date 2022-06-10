package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author birdBro
 * @since 2022-04-20
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class SessionUser extends Model<SessionUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    /**
     * 账号
     */
    private String account;


    /**
     * 登录时间
     */
    private Date timeIn;

    /**
     * 失效时间
     */
    private Date timeOut;

    /**
     * IP
     */
    private String ip;

    /**
     * session
     */
    private String session;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 引擎
     */
    private String engine;

    /**
     * 通讯状态
     */
    private Integer onlineIm;

    /**
     * 移动端
     */
    private Boolean mobile;


    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Integer updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
