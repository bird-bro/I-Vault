package com.microworld.vault.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microworld.vault.modules.log.entity.LogEvent;
import com.microworld.vault.modules.log.mapper.LogEventMapper;
import com.microworld.vault.modules.log.service.ILogEventService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-20
 */
@Service
public class LogEventServiceImpl extends ServiceImpl<LogEventMapper, LogEvent> implements ILogEventService {

}
