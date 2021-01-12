package com.wenyu.blog.service.impl;

import com.wenyu.blog.NotFoundException;
import com.wenyu.blog.dao.BlogRepository;
import com.wenyu.blog.model.Blog;
import com.wenyu.blog.model.Type;
import com.wenyu.blog.service.BLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Author:wenyu
 * 2021/1/6
 */
@Service
public class BlogServiceImpl implements BLogService {

    @Resource
    private BlogRepository blogRepository;

    @Override
    public Optional<Blog> getBlog(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {


        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            //root:具体对象  查询哪个表
            //CriteriaQuery：条件容器  查询哪些字段，排序是什么
            //CriteriaBuilder：具体表达式 字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()==true) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                    return null;
            }
        }, pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);//浏览次数
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if(b==null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(b, blog);
        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }
}
