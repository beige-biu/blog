package com.wenyu.blog.service;


import com.wenyu.blog.model.User;

/**
 * Author:wenyu
 * 2020/12/23
 */
public interface UserService {

  User checkUser(String username, String password);
}
