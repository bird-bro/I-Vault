package com.microworld.vault.modules.log.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.vault.modules.log.entity.LogSign;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-19
 */
public interface ILogSignService extends IService<LogSign> {

    /**
     * 记录登录日志
     * @author: bird
     * @date: 2022-4-20 10:34
     * @param:
     * @return:
     **/
    Boolean createIn(int uid, HttpRequestInfo httpRequest);

    /**
     * 记录登出日志
     * @author: bird
     * @date: 2022-4-20 10:35
     * @param:
     * @return:
     **/
    Boolean createOut(int uid, HttpRequestInfo httpRequest);

}
