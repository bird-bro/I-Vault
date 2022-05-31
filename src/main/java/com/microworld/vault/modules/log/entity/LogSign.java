package com.microworld.vault.modules.log.entity;

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
public class LogSign extends Model<LogSign> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String info;

    /**
     * UID
     */
    private Integer uid;

    private String ip;

    private String trace;

    private String session;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 是否移动端
     */
    private Boolean mobile;

    /**
     * 引擎
     */
    private String engine;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 强制退出 1
     */
    private Integer forcedOut;

    /**
     * 强制退出时间
     */
    private Date forcedTime;


    @TableLogic
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDelete;

    /**
     * 记录时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
