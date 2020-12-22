package com.wenyu.blog.mapper;

import com.wenyu.blog.model.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}