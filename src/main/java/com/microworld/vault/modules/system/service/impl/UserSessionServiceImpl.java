package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microworld.vault.modules.system.entity.UserSession;
import com.microworld.vault.modules.system.mapper.UserSessionMapper;
import com.microworld.vault.modules.system.service.IUserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-19
 */
@Slf4j
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession> implements IUserSessionService {




    @Override
    public Boolean remove(String account) {
        LambdaUpdateWrapper<UserSession> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserSession::getAccount, account);
        return this.remove(wrapper);
    }

    @Override
    public Boolean remove(int uid) {
        LambdaUpdateWrapper<UserSession> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserSession::getUid, uid);
        return this.remove(wrapper);
    }
}
