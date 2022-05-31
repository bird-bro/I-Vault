package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.tools.CharsTool;
import com.bird.common.tools.ObjectTool;
import com.microworld.vault.enums.EnableEnum;
import com.microworld.vault.modules.dict.service.IAccessoryService;
import com.microworld.vault.modules.system.entity.SysOrg;
import com.microworld.vault.modules.system.mapper.SysOrgMapper;
import com.microworld.vault.modules.system.request.OrgCreateRequest;
import com.microworld.vault.modules.system.request.OrgPageRequest;
import com.microworld.vault.modules.system.request.OrgUpdateRequest;
import com.microworld.vault.modules.system.service.ISysOrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
@Slf4j
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {




    @Override
    public Integer create(OrgCreateRequest request) {
        SysOrg org = SysOrg.builder()
                .enable(EnableEnum.TRUE.getKey())
                .build();
        ObjectTool.copyPropertiesIgnoreNull(request, org);
        if(StringUtils.isNotBlank(request.getNameShort())){
            org.setInitial(CharsTool.oneChars(request.getNameShort()));
        }
        if(this.save(org)){
            return org.getOid();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0100,"create org is fail ！");
        }
    }


    @Override
    public Boolean update(OrgUpdateRequest request) {
        SysOrg org = SysOrg.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request, org);
        return this.updateById(org);
    }


    @Override
    public SysOrg query(Integer id) {
        return this.getById(id);
    }


    @Override
    public IPage<SysOrg> queryPage(OrgPageRequest request) {
        return null;
    }
}
