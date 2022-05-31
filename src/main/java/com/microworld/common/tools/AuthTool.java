package com.microworld.common.tools;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.bird.common.tools.RsaTool;
import com.bird.common.entity.PublicKeyMap;
import com.microworld.common.EncryptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

/**
 * 密码工具
 * @author birdbro
 * @date 15:11 2022-4-28
 **/
@Slf4j
public class AuthTool {

    /**
     * 获取公钥,供前端加密密码使用
     * @param publicKeyPair redis中存储的公钥
     * @return 秘钥对
     */
    public static PublicKeyMap getKeyPair(String publicKeyPair){
        PublicKeyMap publicKeyMap = new PublicKeyMap();
        KeyPair keyPair = RsaTool.generateKeyPair();
        publicKeyMap = RsaTool.getPublicKeyMap(keyPair);
        return publicKeyMap;
    }

    /**
     * 哈希加密
     * @param account 账号
     * @param password 密码
     * @return 后端加密的密码
     */
    public static String getEncrypt(String account, String password) {
        return new SimpleHash(EncryptionEnum.ALGORITHMNAME.getValue(), password,
                ByteSource.Util.bytes(account + EncryptionEnum.SALT.getValue()),
                Integer.parseInt(EncryptionEnum.HASHITERATIONS.getValue())).toHex();
    }


    /**
     * 密码加密(加密写入数据库的密码)
     * @param account 登录账号
     * @param password 前端加密的密码
     * @param key 密钥
     * @return 后端加密的加密密码
     * @throws Exception
     */
    public static String aesPassword(String account, String password, String key) throws Exception{
        //真实密码
        String realPwd = null;
        //使用密钥将前端传来的加密密码进行解密
        AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
        realPwd = aes.encryptHex(password);
        if(StringUtils.isBlank(realPwd)) {
            return null;
        }
        //返回使用账号和原始密码进行后台加密的密码
        return getEncrypt(account, realPwd);
    }


    /**
     * 校验密码
     * @param account 账号
     * @param password 前端加密码
     * @param password_db 数据库中存储的密码
     * @param key 密钥
     * @return boolean
     */
    public static boolean aesCheckPassword(String account, String password, String password_db, String key){
        //真实密码
        String realPwd = null;
        //使用密钥将前端传来的加密密码进行解密
        AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
        realPwd = aes.encryptHex(password);
        //使用账号和原始密码进行后台加密的密码
        password = getEncrypt(account, realPwd);
        //校验加密密码与数据库从存储的密码是否相等
        return password_db.equals(password);
    }
}
