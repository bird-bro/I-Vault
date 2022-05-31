package com.microworld.vault.modules.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.dict.entity.DictCity;

import java.util.List;

/**
 * <p>
 * 城市行政区 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-11
 */
public interface IDictCityService extends IService<DictCity> {



    /**
     * 查询省级list
     * @author: bird
     * @date: 2022-4-13 11:14
     * @param:
     * @return:
     **/
    List<DictCity> queryListToProvince();


    /**
     * 查询市/县级list
     * @author: bird
     * @date: 2022-4-13 11:14
     * @param:
     * @return:
     **/
    List<DictCity> queryList(Integer id);


    /**
     * 查询城市树结构
     * @author: bird
     * @date: 2022-4-21 9:56
     * @param:
     * @return:
     **/
    List<DictCity> queryTree();


    /**
     * 查询地址
     * @author: bird
     * @date: 2022-4-20 11:11
     * @param: ip
     * @return:
     **/
    String queryAddrByIp(String ip);

}
