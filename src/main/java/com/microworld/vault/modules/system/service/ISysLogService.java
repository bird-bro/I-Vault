package com.microworld.vault.modules.system.service;

import com.microworld.vault.modules.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-06-10
 */
public interface ISysLogService extends IService<SysLog> {

    /**
    * 
    * @author: birdbro
    * @date: 2022-6-13 14:15
    * @param:
    * @return: 
    **/
    Boolean create(SysLog info);

}
