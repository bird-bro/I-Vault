package com.microworld.common.aspect;

import com.microworld.vault.modules.system.entity.LogInfo;
import com.microworld.vault.modules.system.service.ILogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author birdbro
 * @date 15:09 2022-4-28
 **/
@Slf4j
public class LogThread implements Runnable {

    private volatile ILogInfoService iLogInfoService;

    private volatile LogInfo logInfo;


    public LogThread(ILogInfoService iLogInfoService, LogInfo logInfo){
        this.iLogInfoService = iLogInfoService;
        this.logInfo = logInfo;
    }

    @Override
    public void run() {
        try {
            log.debug("-- aspect log run ");
            if(ObjectUtils.isNotEmpty(logInfo)) {
                this.iLogInfoService.save(logInfo);
            }
        }catch (Exception e){
            log.warn("-- aspect log run ERROR: "+e.getMessage());
        }
    }

}
