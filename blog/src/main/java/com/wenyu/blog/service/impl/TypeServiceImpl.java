package com.wenyu.blog.service.impl;

import com.wenyu.blog.NotFoundException;
import com.wenyu.blog.dao.TypeRepository;
import com.wenyu.blog.mapper.TypeMapper;
import com.wenyu.blog.model.Type;
import com.wenyu.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Author:wenyu
 * 2020/12/26
 */
@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeRepository typeRepository;

    @Resource
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Optional<Type> getType(Long id) {
        return typeRepository.findById(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {

        return  typeRepository.findAll(pageable);
    }


    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeMapper.selectById(id);
        if(t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        return typeMapper.saveType(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
