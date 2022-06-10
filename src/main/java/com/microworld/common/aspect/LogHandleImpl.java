package com.microworld.common.aspect;

import com.bird.common.aspect.AbstractLogHandle;
import com.microworld.vault.modules.system.entity.LogInfo;
import com.microworld.vault.modules.system.service.ILogInfoService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author birdbro
 * @date 14:53 2022-4-28
 **/
@Component
public class LogHandleImpl extends AbstractLogHandle<LogInfo> {

    @Resource
    private ILogInfoService iLogInfoService;

    public LogHandleImpl(ThreadPoolTaskExecutor threadPoolTaskExecutor){
        super(threadPoolTaskExecutor);
    }


    @Override
    public void persistenceLog(LogInfo t) throws Exception {
        threadPoolTaskExecutor.execute(new LogThread(iLogInfoService, t));
    }

}
