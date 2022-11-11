package com.microworld.vault.modules.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.BusinessEnum;
import com.bird.common.tools.CharsTool;
import com.bird.common.tools.ObjectTool;
import com.microworld.vault.modules.dict.entity.DictType;
import com.microworld.vault.modules.dict.mapper.DictTypeMapper;
import com.microworld.vault.modules.dict.request.DictTypeCreateRequest;
import com.microworld.vault.modules.dict.request.DictTypePageRequest;
import com.microworld.vault.modules.dict.request.DictTypeUpdateRequest;
import com.microworld.vault.modules.dict.service.IDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典类型 服务实现类
 * </p>
 *
 * @author birdBro
 * @since 2022-04-12
 */
@Slf4j
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {


    @Override
    public Integer create(DictTypeCreateRequest request) {
        DictType dictType = DictType.builder()
                .initial(CharsTool.oneChars(request.getName()))
                .build();
        ObjectTool.copyPropertiesIgnoreNull(request,dictType);
        if(this.save(dictType)){
            return dictType.getId();
        }else {
            throw new BusinessException(BusinessEnum.ERROR_B0001,"create dict type is fail ！");
        }
    }


    @Override
    public Boolean update(DictTypeUpdateRequest request) {
        DictType dictType = DictType.builder().build();
        ObjectTool.copyPropertiesIgnoreNull(request,dictType);
        return this.updateById(dictType);
    }


    @Override
    public List<DictType> queryList() {
        LambdaQueryWrapper<DictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(DictType::getInitial);
        return this.list(wrapper);
    }


    @Override
    public IPage<DictType> queryPage(DictTypePageRequest request) {
        LambdaQueryWrapper<DictType> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ObjectUtils.isNotEmpty(request.getCls()), DictType::getCls, request.getCls())
                .orderByAsc(DictType::getInitial);
        Page<DictType> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page,wrapper);
    }


    @Override
    public Boolean exist (String name) {
        if(StringUtils.isBlank(name)){
            return false;
        }
        LambdaQueryWrapper<DictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictType::getName, name);
        return this.count(wrapper)>0;
    }
}
