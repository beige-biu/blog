package com.wenyu.blog.web;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:wenyu
 * 2020/11/9
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){

        return "index";
    }
}
