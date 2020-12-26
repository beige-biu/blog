package com.wenyu.blog.service;

import com.wenyu.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author:wenyu
 * 2020/12/26
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void  deleteType(Long id);
}
