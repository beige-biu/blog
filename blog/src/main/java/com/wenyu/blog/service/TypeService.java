package com.wenyu.blog.service;

import com.wenyu.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author:wenyu
 * 2020/12/26
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void  deleteType(Long id);

    Type selectByName(String name);

    Type selectByPrimaryKey(Long id);

    List<Type> listType();
}
