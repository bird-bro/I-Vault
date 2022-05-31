package com.microworld.vault.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.entity.UserSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-19
 */
public interface IUserSessionService extends IService<UserSession> {


    Boolean create(SysUser user, HttpRequestInfo info);

    /**
     * 删除session记录
     * @author: bird
     * @date: 2022-4-20 10:27
     * @param: account 账号
     * @return: boolean
     **/
    Boolean remove (String account);

    /**
     * 删除session记录
     * @author: bird
     * @date: 2022-4-20 10:27
     * @param: uid 用户id
     * @return: boolean
     **/
    Boolean remove (int uid);



}
