package com.wenyu.blog.dao;

import com.wenyu.blog.model.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author:wenyu
 * 2020/12/28
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);


    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
