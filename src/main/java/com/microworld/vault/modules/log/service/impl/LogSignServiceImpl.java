package com.microworld.vault.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.entity.HttpRequestInfo;
import com.microworld.vault.modules.dict.service.IDictCityService;
import com.microworld.vault.modules.log.entity.LogSign;
import com.microworld.vault.modules.log.mapper.LogSignMapper;
import com.microworld.vault.modules.log.service.ILogSignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-19
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Service
public class LogSignServiceImpl extends ServiceImpl<LogSignMapper, LogSign> implements ILogSignService {

    private final IDictCityService iDictCityService;


    @Override
    public Boolean createIn(int uid, HttpRequestInfo httpRequest) {

        LogSign sign = LogSign.builder()
                .type("sign_in")
                .uid(uid)
                .ip(httpRequest.getIp4())
                //.trace(iDictCityService.queryAddrByIp(httpRequest.getIp4()))
                .session(httpRequest.getSession())
                .browser(httpRequest.getBrowser())
                .engine(httpRequest.getEngine())
                .os(httpRequest.getOs())
                .mobile(httpRequest.getMobile())
                .build();
        return this.save(sign);
    }

    @Override
    public Boolean createOut(int uid, HttpRequestInfo httpRequest) {


        LogSign sign = LogSign.builder()
                .type("sign_out")
                .uid(uid)
                .ip(httpRequest.getIp4())
                //.trace(iDictCityService.queryAddrByIp(httpRequest.getIp4()))
                .session(httpRequest.getSession())
                .browser(httpRequest.getBrowser())
                .engine(httpRequest.getEngine())
                .os(httpRequest.getOs())
                .mobile(httpRequest.getMobile())
                .build();
        return this.save(sign);
    }
}
