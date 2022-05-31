package com.microworld.vault.modules.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.dict.entity.DictData;
import com.microworld.vault.modules.dict.request.DictDataCreateRequest;
import com.microworld.vault.modules.dict.request.DictDataPageRequest;
import com.microworld.vault.modules.dict.request.DictDataUpdateRequest;
import com.microworld.vault.modules.dict.response.DictDataResponse;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典数据 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
public interface IDictDataService extends IService<DictData> {
    
    
    /**
     * 添加字典数据
     * @author: bird
     * @date: 2022-4-13 10:34
     * @param:
     * @return:
     **/
    Integer create(DictDataCreateRequest request);
    
    
    /**
     * 修改字典数据
     * @author: bird
     * @date: 2022-4-13 10:34
     * @param:
     * @return:
     **/
    Boolean update(DictDataUpdateRequest request);
    
    
    /**
     * 查询字典数据 list
     * @author: bird
     * @date: 2022-4-13 10:34
     * @param:
     * @return:
     **/
    List<DictDataResponse> queryList(Integer id);


    /**
     * 查询字典数据 list map
     * @author: bird
     * @date: 2022-4-13 13:30
     * @param: 类型ids
     * @return:
     **/
    Map<Integer, List<DictDataResponse>> queryListMap(List<Integer> ids);
    
    
    /**
     * 查询字典数据 page
     * @author: bird
     * @date: 2022-4-13 10:34
     * @param:
     * @return:
     **/
    IPage<DictData> queryPage(DictDataPageRequest request);
    
    
    /**
     * 检查字典数据是否已经存在
     * @author: bird
     * @date: 2022-4-13 10:34
     * @param:
     * @return:
     **/
    Boolean exist(String value);

}
