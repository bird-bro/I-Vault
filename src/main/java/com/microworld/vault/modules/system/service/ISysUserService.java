package com.microworld.vault.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.request.UserCreateRequest;
import com.microworld.vault.modules.system.request.UserPageRequest;
import com.microworld.vault.modules.system.request.UserUpdateRequest;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-15
 */
public interface ISysUserService extends IService<SysUser> {


    /**
     * 添加用户信息
     * @author: bird
     * @date: 2022-4-18 10:08
     * @param: request
     * @return: Integer
     **/
    Integer create(UserCreateRequest request);


    /**
     * 修改用户信息
     * @author: bird
     * @date: 2022-4-18 10:08
     * @param: request
     * @return: boolean
     **/
    Boolean update(UserUpdateRequest request);


    /**
     * 更新登录记录
     * @author: bird
     * @date: 2022-4-18 10:08
     * @param: request
     * @return: boolean
     **/
    Boolean renewSign(int uid, String ip, int no);


    /**
     * 查询用户信息
     * @author: bird
     * @date: 2022-4-20 9:27
     * @param: account 账号/手机号/邮箱
     * @return: SysUser
     **/
    SysUser query(String account);


    /**
     * 查询用户信息-Page
     * @author: bird
     * @date: 2022-4-18 10:08
     * @param: request
     * @return: IPage<SysUser>
     **/
    IPage<SysUser> queryPage(UserPageRequest request);

    /**
     * 判断账号是否已存在
     * @author: bird
     * @date: 2022-4-18 10:11
     * @param: account 账号
     * @return: boolean
     **/
    Boolean existByAccount(String account);

    /**
     * 判断手机号是否已存在
     * @author: bird
     * @date: 2022-4-18 10:11
     * @param: phone 手机号
     * @return: boolean
     **/
    Boolean existByPhone(String phone);

    /**
     * 判断邮箱是否已存在
     * @author: bird
     * @date: 2022-4-18 10:11
     * @param: email 邮箱
     * @return: boolean
     **/
    Boolean existByEmail(String email);

    /**
     * 判断身份证是否已存在
     * @author: bird
     * @date: 2022-4-18 10:11
     * @param: idNo 身份证号
     * @return: boolean
     **/
    Boolean existByIdNo(String idNo);

}
