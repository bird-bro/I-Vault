package com.microworld.common.aspect;

import com.microworld.vault.modules.system.entity.SysLog;
import com.microworld.vault.modules.system.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author birdbro
 * @date 15:09 2022-4-28
 **/
@Slf4j
public class LogThread implements Runnable {

    private volatile ISysLogService iLogInfoService;

    private volatile SysLog logInfo;


    public LogThread(ISysLogService iLogInfoService, SysLog logInfo){
        this.iLogInfoService = iLogInfoService;
        this.logInfo = logInfo;
    }

    @Override
    public void run() {
        try {
            log.debug("-- aspect log run ");
            if(ObjectUtils.isNotEmpty(logInfo)) {
                this.iLogInfoService.create(logInfo);
            }
        }catch (Exception e){
            log.warn("-- aspect log run ERROR: "+e.getMessage());
        }
    }

}
