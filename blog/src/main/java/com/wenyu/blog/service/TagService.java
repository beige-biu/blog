package com.wenyu.blog.service;

import com.wenyu.blog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author:wenyu
 * 2021/1/6
 */
public interface TagService {
    Tag saveTag(Tag tag);

    List<Tag> getTag(Long id);

    Tag getOneTag(Long id);

    Page<Tag>  tagList(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    Tag selectByName(String name);

    Tag selectByPrimaryKey(Long id);
}
