package com.wenyu.blog.service.impl;

import com.wenyu.blog.mapper.UserMapper;
import com.wenyu.blog.model.User;
import com.wenyu.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author:wenyu
 * 2020/12/23
 */
@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserMapper userMapper;
    @Override
    public User checkUser(String username, String password) {

        User user = userMapper.findByUsernameAndPassword(username,password);
        return user;
    }
}
