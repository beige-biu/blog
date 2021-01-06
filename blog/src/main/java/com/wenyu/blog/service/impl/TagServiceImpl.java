package com.wenyu.blog.service.impl;

import com.wenyu.blog.NotFoundException;
import com.wenyu.blog.dao.TagRepository;
import com.wenyu.blog.mapper.TagMapper;
import com.wenyu.blog.model.Tag;
import com.wenyu.blog.service.TagService;
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
 * 2021/1/6
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Resource
    private TagMapper tagMapper;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Optional<Tag> getTag(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Page<Tag> tagList(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if(t==null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag selectByName(String name) {
        return tagRepository.findByname(name);
    }

    @Override
    public Tag selectByPrimaryKey(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }
}
