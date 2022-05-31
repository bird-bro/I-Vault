package com.microworld.common.aspect;

import com.bird.common.aspect.AbstractLogHandle;
import com.microworld.vault.modules.log.entity.LogEvent;
import com.microworld.vault.modules.log.service.ILogEventService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

/**
 *
 * @author birdbro
 * @date 14:53 2022-4-28
 **/
public class LogHandleImpl extends AbstractLogHandle<LogEvent> {

    @Resource
    private ILogEventService iLogEventService;

    public LogHandleImpl(ThreadPoolTaskExecutor threadPoolTaskExecutor){
        super(threadPoolTaskExecutor);
    }


    @Override
    public void persistenceLog(LogEvent t) throws Exception {
        threadPoolTaskExecutor.execute(new LogThread(iLogEventService, t));
    }

}
