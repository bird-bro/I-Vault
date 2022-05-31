package com.microworld.vault.modules.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@Data
@EqualsAndHashCode(callSuper = false)
public class LogEvent extends Model<LogEvent> {

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

    private String uid;

    private String account;

    private String session;

    private String ip;

    private String pack;

    private String browser;

    private String function;

    private Integer spend;

    private String url;

    private String param;

    private String exception;

    private String os;

    private Boolean mobile;

    private Date createTime;

    private Boolean isDelete;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
