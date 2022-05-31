package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microworld.vault.modules.system.entity.OrgUser;
import com.microworld.vault.modules.system.mapper.OrgUserMapper;
import com.microworld.vault.modules.system.service.IOrgUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
@Service
public class OrgUserServiceImpl extends ServiceImpl<OrgUserMapper, OrgUser> implements IOrgUserService {

}
