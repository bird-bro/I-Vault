package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.vault.modules.system.entity.SessionUser;
import com.microworld.vault.modules.system.mapper.SessionUserMapper;
import com.microworld.vault.modules.system.response.UserInfoResponse;
import com.microworld.vault.modules.system.service.ISessionUserService;
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
public class SessionUserServiceImpl extends ServiceImpl<SessionUserMapper, SessionUser> implements ISessionUserService {


    @Override
    public Boolean create(UserInfoResponse user, HttpRequestInfo info) {
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        SessionUser session = SessionUser.builder()
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
        LambdaUpdateWrapper<SessionUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SessionUser::getAccount, account);
        return this.remove(wrapper);
    }

    @Override
    public Boolean remove(int uid) {
        LambdaUpdateWrapper<SessionUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SessionUser::getUid, uid);
        return this.remove(wrapper);
    }
}
