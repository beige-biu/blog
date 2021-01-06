package com.wenyu.blog.dao;

import com.wenyu.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author:wenyu
 * 2021/1/6
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByname(String name);
}
