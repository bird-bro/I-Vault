package com.microworld.vault.modules.system.service;

import com.microworld.vault.modules.system.entity.UserSession;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-06-13
 */
public interface IUserSessionService extends IService<UserSession> {

    Boolean remove(String account);

    Boolean remove(int uid);

}
