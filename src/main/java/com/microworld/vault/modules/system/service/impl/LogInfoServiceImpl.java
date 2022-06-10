package com.microworld.vault.modules.system.service.impl;

import com.microworld.vault.modules.system.entity.LogInfo;
import com.microworld.vault.modules.system.mapper.LogInfoMapper;
import com.microworld.vault.modules.system.service.ILogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-06-10
 */
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements ILogInfoService {

}
