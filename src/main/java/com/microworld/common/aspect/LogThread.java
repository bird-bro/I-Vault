package com.microworld.common.aspect;

import com.microworld.vault.modules.log.entity.LogEvent;
import com.microworld.vault.modules.log.service.ILogEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author birdbro
 * @date 15:09 2022-4-28
 **/
@Slf4j
public class LogThread implements Runnable {

    private volatile ILogEventService iLogEventService;

    private volatile LogEvent logEvent;

    public LogThread(ILogEventService iLogEventService, LogEvent logEvent){
        this.iLogEventService = iLogEventService;
        this.logEvent = logEvent;
    }

    @Override
    public void run() {
        try {
            log.debug("-- aspect log run ");
            if(ObjectUtils.isNotEmpty(logEvent)) {
                this.iLogEventService.save(logEvent);
            }
        }catch (Exception e){
            log.warn("-- aspect log run ERROR: "+e.getMessage());
        }
    }

}
