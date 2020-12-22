package com.wenyu.blog.mapper;

import com.wenyu.blog.model.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}