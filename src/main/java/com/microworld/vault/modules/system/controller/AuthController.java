package com.microworld.vault.modules.system.controller;

import cn.hutool.core.lang.Validator;
import com.bird.common.annotation.Log;
import com.bird.common.annotation.PassToken;
import com.bird.common.entity.PublicKeyMap;
import com.bird.common.exception.AssertException;
import com.bird.common.redis.RedisUtil;
import com.bird.common.tools.RsaTool;
import com.microworld.common.tools.AuthTool;
import com.microworld.common.Constants;
import com.microworld.vault.modules.system.request.SignInRequest;
import com.microworld.vault.modules.system.request.SignOutRequest;
import com.microworld.vault.modules.system.request.SignUpRequest;
import com.microworld.vault.modules.system.response.UserInfoResponse;
import com.microworld.vault.modules.system.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

/**
 * 身份认证
 * @author bird
 * @date 2022-4-15 15:46
 **/
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "A-01_Auth")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final RedisUtil redisUtil;

    private final IAuthService iAuthService;


    @PassToken
    @GetMapping("/keyPair")
    @ApiOperation(value = "获取公钥", notes = "get keyPair")

    public PublicKeyMap keyPair() throws Exception {

        PublicKeyMap publicKeyMap = new PublicKeyMap();
        String publicKeyPair = redisUtil.get(Constants.RAS_PUBLIC_KEY);

        if (StringUtils.isBlank(publicKeyPair)){
            publicKeyMap = AuthTool.getKeyPair(publicKeyPair);
            KeyPair keyPair = RsaTool.generateKeyPair();
            redisUtil.set(Constants.RAS_PUBLIC_KEY, publicKeyMap.getExponent() + "," + publicKeyMap.getOwnModulus());
            RSAPrivateKey rsaPrivateKey = RsaTool.getDefaultPrivateKey(keyPair);
            byte[] privateKeyByte = rsaPrivateKey.getEncoded();
            redisUtil.set(Constants.RAS_PRIVATE_KEY, (Base64.getEncoder().encodeToString(privateKeyByte)));
        }else {
            publicKeyMap.setExponent(publicKeyPair.split(",")[0]);
            publicKeyMap.setOwnModulus(publicKeyPair.split(",")[1]);
        }
        return publicKeyMap;
    }


    @PassToken
    @Log(value = "注册用户", type = "POST")
    @PostMapping("/sign/up")
    @ApiOperation(value = "注册用户")
    public Integer signUp(@Validated @RequestBody SignUpRequest request, BindingResult bindingResult){
        AssertException.validated(bindingResult);
        return null;
    }


    @PassToken
    @Log(value = "登录", type = "POST")
    @PostMapping("/sign/in")
    @ApiOperation(value = "登录")
    public UserInfoResponse signIn(@Validated @RequestBody SignInRequest sign, HttpServletRequest request, HttpServletResponse response){
        return iAuthService.signIn(sign, request, response);
    }


    @Log(value = "登出", type = "POST")
    @PostMapping("/sign/out")
    @ApiOperation(value = "登出")
    public Boolean signOut(@Validated @RequestBody SignOutRequest sign, HttpServletRequest request, HttpServletResponse response){
        return iAuthService.signOut(sign, request, response);
    }


    @GetMapping("/check/phone/{phone}")
    @ApiOperation(value = "校验手机号格式")
    @ApiImplicitParam(name = "phone", value = "手机号", dataTypeClass = Integer.class, paramType = "path", required = true)
    public boolean isPhone(String phone){
        return Validator.isMobile(phone);
    }


    @GetMapping("/check/email/{email}")
    @ApiOperation(value = "校验邮箱格式")
    @ApiImplicitParam(name = "email", value = "邮箱", dataTypeClass = Integer.class, paramType = "path", required = true)
    public boolean isEmail(String email){
        return Validator.isEmail(email);
    }


    @GetMapping("/check/birthday/{birthday}")
    @ApiOperation(value = "校验生日格式")
    @ApiImplicitParam(name = "birthday", value = "生日", dataTypeClass = Integer.class, paramType = "path", required = true)
    public boolean isBirthday(String birthday){
        return Validator.isBirthday(birthday);
    }







}
