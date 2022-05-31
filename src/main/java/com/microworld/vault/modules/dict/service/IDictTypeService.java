package com.microworld.vault.modules.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microworld.vault.modules.dict.entity.DictType;
import com.microworld.vault.modules.dict.request.DictTypeCreateRequest;
import com.microworld.vault.modules.dict.request.DictTypePageRequest;
import com.microworld.vault.modules.dict.request.DictTypeUpdateRequest;

import java.util.List;

/**
 * <p>
 * 字典类型 服务类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
public interface IDictTypeService extends IService<DictType> {

    /**
     * 添加字典类型
     * @author: bird
     * @date: 2022-4-13 9:06
     * @param:
     * @return:
     **/
    Integer create(DictTypeCreateRequest request);


    /**
     * 修改字典类型
     * @author: bird
     * @date: 2022-4-13 9:08
     * @param:
     * @return:
     **/
    Boolean update(DictTypeUpdateRequest request);


    /**
     * 查询字典类型list
     * @author: bird
     * @date: 2022-4-13 9:08
     * @param:
     * @return:
     **/
    List<DictType> queryList();


    /**
     * 查询字典类型page
     * @author: bird
     * @date: 2022-4-13 9:11
     * @param:
     * @return:
     **/
    IPage<DictType> queryPage(DictTypePageRequest request);


    /**
     * 检查字典类型是否已经存在
     * @author: bird
     * @date: 2022-4-13 9:22
     * @param:
     * @return:
     **/
    Boolean exist(String name);

}
