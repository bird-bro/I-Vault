package com.microworld.vault.modules.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.microworld.vault.modules.dict.entity.DictData;
import com.microworld.vault.modules.dict.response.DictDataResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典数据 Mapper 接口
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
public interface DictDataMapper extends BaseMapper<DictData> {


    /**
     *
     * @author: bird
     * @date: 2022-4-13 11:31
     * @param:
     * @return:
     **/
    List<DictDataResponse> list (@Param("typeId") Integer typeId);

}
