package com.microworld.vault.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.system.entity.SysOrg;
import com.microworld.vault.modules.system.request.OrgCreateRequest;
import com.microworld.vault.modules.system.request.OrgPageRequest;
import com.microworld.vault.modules.system.request.OrgUpdateRequest;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
public interface ISysOrgService extends IService<SysOrg> {


    /**
    * 添加机构信息
    * @author: birdbro
    * @date: 2022-4-25 16:47
    * @param:
    * @return:
    **/
    Integer create(OrgCreateRequest request);


    /**
    * 修改机构信息
    * @author: birdbro
    * @date: 2022-4-25 16:47
    * @param:
    * @return:
    **/
    Boolean update(OrgUpdateRequest request);


    /**
    * 查询机构信息
    * @author: birdbro
    * @date: 2022-4-25 16:47
    * @param:
    * @return:
    **/
    SysOrg query(Integer id);


    /**
    * 查询机构信息--page
    * @author: birdbro
    * @date: 2022-4-25 16:47
    * @param:
    * @return:
    **/
    IPage<SysOrg> queryPage(OrgPageRequest request);














}
