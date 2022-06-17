package com.microworld.vault.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;;
import com.bird.common.exception.AssertException;
import com.bird.common.annotation.Log;
import com.microworld.vault.modules.system.entity.SysUser;
import com.microworld.vault.modules.system.request.UserCreateRequest;
import com.microworld.vault.modules.system.request.UserPageRequest;
import com.microworld.vault.modules.system.request.UserUpdateRequest;
import com.microworld.vault.modules.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author birdBro
 * @since 2022-04-11
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "A-02_User")
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController {


    private final ISysUserService iUserService;


    @Log(value = "新增用户", type = "POST")
    @PostMapping("/create")
    @ApiOperation(value = "新增用户")
    public Integer create (@Validated @RequestBody UserCreateRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iUserService.create(request);
    }


    @Log(value = "修改用户", type = "POST")
    @PostMapping("/update")
    @ApiOperation(value = "修改用户")
    public Boolean update(@Validated @RequestBody UserUpdateRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        AssertException.isEmail(request.getEmail(), "");
        return iUserService.update(request);
    }


    @Log(value = "删除用户", type = "DELETE")
    @DeleteMapping(path = "/{uid}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "uid", value = "用户ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public Boolean remove(@PathVariable(value = "uid") Integer uid){
        return iUserService.removeById(uid);
    }


    @GetMapping(path = "/{uid}")
    @ApiOperation(value = "查询用户-entity ")
    @ApiImplicitParam(name = "uid", value = "用户ID", dataTypeClass = Integer.class, paramType = "path", required = true)
    public SysUser query(@PathVariable(value = "uid") Integer uid) {
        return iUserService.getById(uid);
    }


    @PostMapping("/page")
    @ApiOperation(value = "查询用户-page")
    public IPage<SysUser> queryPage(@Validated @RequestBody UserPageRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return iUserService.queryPage(request);
    }


    @GetMapping("/exist/account/{account}")
    @ApiOperation(value = "判断账号是否已存在")
    @ApiImplicitParam(name = "account", value = "账号", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByAccount(@PathVariable(value = "account") String account) {
        return iUserService.existByAccount(account);
    }


    @GetMapping("/exist/phone/{phone}")
    @ApiOperation(value = "判断手机号是否已存在")
    @ApiImplicitParam(name = "phone", value = "手机号", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByPhone(@PathVariable(value = "phone") String phone) {
        return iUserService.existByPhone(phone);
    }


    @GetMapping("/exist/email/{email}")
    @ApiOperation(value = "判断邮箱是否已存在")
    @ApiImplicitParam(name = "email", value = "邮箱", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByEmail(@PathVariable(value = "email") String email) {
        return iUserService.existByEmail(email);
    }


    @GetMapping("/exist/idno/{idNo}")
    @ApiOperation(value = "判断身份证号是否已存在")
    @ApiImplicitParam(name = "idNo", value = "身份证号", dataTypeClass = String.class, paramType = "path", required = true)
    public Boolean existByIdNo(@PathVariable(value = "idNo") String idNo) {
        return iUserService.existByIdNo(idNo);
    }








    }

