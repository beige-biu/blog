package com.wenyu.blog.service.impl;

import com.wenyu.blog.mapper.TypeMapper;
import com.wenyu.blog.model.Type;
import com.wenyu.blog.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author:wenyu
 * 2020/12/26
 */
@Service
public class TypeServiceImpl implements TypeService {


    @Resource
    private TypeMapper typeMapper;


    @Override
    public Type saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(long id) {
        Type type = typeMapper.selectById(id);
        return type;
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        //return typeMapper.findAll;
        return  null;
    }

    @Override
    public Type updateType(Long id, Type type) {
        return null;
    }

    @Override
    public void deleteType(Long id) {

    }
}
