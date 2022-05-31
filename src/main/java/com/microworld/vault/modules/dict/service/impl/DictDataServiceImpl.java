package com.microworld.vault.modules.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.tools.CharsTool;
import com.bird.common.tools.ObjectTool;
import com.microworld.vault.modules.dict.entity.DictData;
import com.microworld.vault.modules.dict.mapper.DictDataMapper;
import com.microworld.vault.modules.dict.request.DictDataCreateRequest;
import com.microworld.vault.modules.dict.request.DictDataPageRequest;
import com.microworld.vault.modules.dict.request.DictDataUpdateRequest;
import com.microworld.vault.modules.dict.response.DictDataResponse;
import com.microworld.vault.modules.dict.service.IDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典数据 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
@Slf4j
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {


    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public Integer create(DictDataCreateRequest request) {
        int sort = 10;
        //字典数据编码
        String code = String.valueOf(request.getTypeId());
        LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictData::getTypeId, request.getTypeId());
        request.setSort(this.count(wrapper));
        if(request.getSort() == 0){
            code = code + "00";
        } else {
            if (request.getSort() < sort){
                code = code + "0" + request.getSort();
            }else {
                code = code + request.getSort();
            }
        }

        //赋值
        DictData dictData = DictData.builder()
                .code(Integer.valueOf(code))
                .initial(CharsTool.oneChars(request.getValue()))
                .build();
        ObjectTool.copyPropertiesIgnoreNull(request,dictData);
        if(this.save(dictData)){
            return dictData.getId();
        }else {
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_B0001,"create dict data is fail ！");
        }
    }


    @Override
    public Boolean update(DictDataUpdateRequest request) {
        DictData dictData = DictData.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request,dictData);
        return this.updateById(dictData);
    }


    @Override
    public List<DictDataResponse> queryList(Integer id) {
        return dictDataMapper.list(id);
    }


    @Override
    public Map<Integer, List<DictDataResponse>> queryListMap(List<Integer> ids) {
        Map<Integer,List<DictDataResponse>> resMap = new HashMap<>();
        for (Integer id : ids){
            List<DictDataResponse> dataList = queryList(id);
            if(ObjectUtils.isNotEmpty(dataList)) {
                resMap.put(id, dataList);
            }
        }
        return resMap;
    }


    @Override
    public IPage<DictData> queryPage(DictDataPageRequest request) {
        LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ObjectUtils.isNotEmpty(request.getTypeId()), DictData::getTypeId, request.getTypeId())
                .orderByAsc(DictData::getSort);
        Page<DictData> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, wrapper);
    }


    @Override
    public Boolean exist(String value) {
        if(StringUtils.isBlank(value)){
            return false;
        }
        LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictData::getValue, value);
        return this.count(wrapper) > 0;
    }


}
