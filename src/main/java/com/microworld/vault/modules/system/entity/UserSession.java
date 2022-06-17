package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author birdBro
 * @since 2022-06-13
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSession extends Model<UserSession> {

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
     * 通讯状态
     */
    private Integer onlineIm;

    /**
     * 移动端
     */
    private Boolean mobile;

    private Boolean isDelete;

    private Date createTime;

    private String engine;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
