package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.entity.UserSession;
import com.microworld.vault.modules.system.mapper.UserSessionMapper;
import com.microworld.vault.modules.system.service.IUserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
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
    public Boolean create(SysUser user, HttpRequestInfo info) {
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        UserSession session = UserSession.builder()
                .uid(user.getUid())
                .account(user.getAccount())
                .build();
        if(ObjectUtils.isNotEmpty(info)){
            session.setSession(info.getSession());
            session.setBrowser(info.getBrowser());
            session.setIp(info.getIp4());
            session.setOs(info.getOs());
            session.setMobile(info.getMobile());
            session.setEngine(info.getEngine());
        }
        return this.save(session);
    }

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
