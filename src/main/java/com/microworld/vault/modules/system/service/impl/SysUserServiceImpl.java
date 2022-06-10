package com.microworld.vault.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.tools.CharsTool;
import com.bird.common.tools.ObjectTool;
import com.microworld.common.Constants;
import com.microworld.vault.enums.EnableEnum;
import com.microworld.vault.modules.dict.service.IAccessoryService;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.mapper.SysUserMapper;
import com.microworld.vault.modules.system.request.UserCreateRequest;
import com.microworld.vault.modules.system.request.UserPageRequest;
import com.microworld.vault.modules.system.request.UserUpdateRequest;
import com.microworld.vault.modules.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-15
 */
@Slf4j
@Service
@Configuration
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private IAccessoryService iAccessoryService;

    @Value("${auth.method}")
    private String method;



    @Override
    public Integer create(UserCreateRequest request) {

        SysUser user = SysUser.builder()
                .enable(EnableEnum.TRUE.getKey())
                .initial(CharsTool.oneChars(request.getName()))
                .build();
        ObjectTool.copyPropertiesIgnoreNull(request, user);
        //生成默认头像
        if(StringUtils.isBlank(user.getHeadIcon())){
            user.setHeadIcon(iAccessoryService.headIcon(StringUtils.substring(request.getName(),0,1)));
        }
        //认证模式
        if(Constants.METHOD_SSO.equals(method)){
            //使用SSO

        }else {
            //使用密码

        }
        if(this.save(user)){
            return user.getUid();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0100,"create user is fail ！");
        }
    }


    @Override
    public Boolean update(UserUpdateRequest request) {
        SysUser user = SysUser.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request, user);
        return this.updateById(user);
    }


    @Override
    public Boolean renewSign(int uid, String ip, int no) {

        LambdaUpdateWrapper<SysUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper
                .set(StringUtils.isNotBlank(ip), SysUser::getInIp, ip)
                .set(SysUser::getInNo, no+1)
                .set(SysUser::getInTime, new Date())
                .eq(SysUser::getUid, uid);
        return this.update(wrapper);
    }

    @Override
    public SysUser query(String account) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(SysUser::getAccount, account)
                .or().eq(SysUser::getPhone, account)
                .or().eq(SysUser::getEmail, account);
        return this.getOne(wrapper);
    }

    @Override
    public IPage<SysUser> queryPage(UserPageRequest request) {
        return null;
    }


    @Override
    public Boolean existByAccount(String account) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, account);
        return this.count(wrapper) > 0;
    }


    @Override
    public Boolean existByPhone(String phone) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getPhone, phone);
        return this.count(wrapper) > 0;
    }


    @Override
    public Boolean existByEmail(String email) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getEmail, email);
        return this.count(wrapper) > 0;
    }


    @Override
    public Boolean existByIdNo(String idNo) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getIdNo, idNo);
        return this.count(wrapper) > 0;
    }
}
