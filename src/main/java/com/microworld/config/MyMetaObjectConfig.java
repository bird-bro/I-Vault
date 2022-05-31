package com.microworld.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bird.common.tools.HttpTool;
import io.swagger.models.auth.In;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author birdbro
 * @date 11:13 2022-4-28
 **/
@Configuration
public class MyMetaObjectConfig  implements MetaObjectHandler {

    private Integer uid;

    @Override
    public void insertFill(MetaObject metaObject){
        uid = HttpTool.getUid();

        this.strictInsertFill(metaObject, "createBy", Integer.class,uid);
        this.strictInsertFill(metaObject, "createTime", Date::new,Date.class);
        this.strictInsertFill(metaObject,"dateYm",String.class, DateUtil.format(new Date(),"yyyyMM"));
        this.strictInsertFill(metaObject,"isDelete",Boolean.class, false);
    }

    @Override
    public void updateFill(MetaObject metaObject){
        uid = HttpTool.getUid();

        this.strictUpdateFill(metaObject, "updateTime", Date::new,Date.class);
        this.strictInsertFill(metaObject, "updateBy", Integer.class,uid);
    }

}
