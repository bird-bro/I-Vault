package com.microworld.vault.modules.system.service.impl;

import com.microworld.vault.modules.system.entity.SysLog;
import com.microworld.vault.modules.system.entity.UserSession;
import com.microworld.vault.modules.system.mapper.SysLogMapper;
import com.microworld.vault.modules.system.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microworld.vault.modules.system.service.IUserSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-06-10
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Resource
    private IUserSessionService iUserSessionService ;

    @Override
    public Boolean create(SysLog info) {
        if(info.getSign()){
            UserSession session = UserSession.builder()
                .session(info.getSession())
                .browser(info.getBrowser())
                .ip(info.getIp())
                .os(info.getOs())
                .mobile(info.getMobile())
                .engine(info.getEngine())
                .account(info.getAccount())
                .timeIn(new Date())
                .build();
            iUserSessionService.save(session);
        }
        return this.save(info);
    }
}
