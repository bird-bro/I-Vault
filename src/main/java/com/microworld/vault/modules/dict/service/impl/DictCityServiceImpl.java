package com.microworld.vault.modules.dict.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microworld.vault.modules.dict.entity.DictCity;
import com.microworld.vault.modules.dict.mapper.DictCityMapper;
import com.microworld.vault.modules.dict.service.IDictCityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 城市行政区 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-11
 */
@Slf4j
@Service
public class DictCityServiceImpl extends ServiceImpl<DictCityMapper, DictCity> implements IDictCityService {

    @Value("${map.ip}")
    private String urlIp;
    @Value("${map.key}")
    private String key;

    @Override
    public List<DictCity> queryListToProvince() {
        LambdaQueryWrapper<DictCity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictCity::getLevel,1).orderByAsc(DictCity::getId);
        return this.list(wrapper);
    }

    @Override
    public List<DictCity> queryList(Integer id) {
        LambdaQueryWrapper<DictCity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictCity::getUpId,id).orderByAsc(DictCity::getId);
        return this.list(wrapper);
    }

    @Override
    public List<DictCity> queryTree() {
        LambdaQueryWrapper<DictCity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(DictCity::getId,100000).orderByAsc(DictCity::getId);
        List<DictCity> cities = this.list(wrapper);
        if(CollectionUtil.isEmpty(cities)){
            return null;
        }
        return cities.stream()
                .filter(parent -> parent.getUpId().equals(100000))
                .peek(child -> child.setChildren(getChildren(cities, child.getId())))
                .collect(Collectors.toList());
    }

    private List<DictCity> getChildren(List<DictCity> cities, Integer parentId){
        return cities.stream()
                .filter(parent -> parent.getUpId().equals(parentId))
                .peek(child -> child.setChildren(getChildren(cities, child.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public String queryAddrByIp(String ip) {
        try {
            if(!Validator.isIpv4(ip)){
                return  null;
            }
            //判断是否为内网地址
            if(NetUtil.isInnerIP(ip)){
                return "Inner"+"(内网)";
            }
            String url =  urlIp + "key=" +key + "&type=4" + "&ip="+ip;
            log.info("-- 调用高德API: " + url);
            String json = HttpUtil.get(url);
            if(StringUtils.isBlank(json)) {
                return null;
            }
            Map obj = JSONObject.parseObject(json, Map.class);
            if (StringUtils.isBlank(obj.get("isp").toString())){
                return null;
            }
            String address = String.format("%s-%s-%s",
                    obj.get("province").toString(),
                    obj.get("city").toString(),
                    obj.get("district").toString());
            return String.format("%s (%s)",address, obj.get("isp").toString());
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }
}
