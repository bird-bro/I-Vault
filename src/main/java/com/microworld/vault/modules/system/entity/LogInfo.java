package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author birdBro
 * @since 2022-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogInfo extends Model<LogInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String logType;

    private String logValue;

    private String logDesc;

    private Boolean logRecord;

    private String logTime;

    private String app;

    private String type;

    private String method;

    private String pack;

    private Integer uid;

    private String account;

    private String session;

    private String ip;

    private String function;

    private String url;

    private String exception;

    /**
     * 浏览器
     */
    private String browser;

    private String os;

    /**
     * 是否移动端
     */
    private Boolean mobile;

    /**
     * 引擎
     */
    private String engine;

    private Date createTime;

    private Boolean isDelete;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
