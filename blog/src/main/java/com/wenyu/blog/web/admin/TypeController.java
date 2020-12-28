package com.wenyu.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:wenyu
 * 2020/12/28
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @GetMapping("/types")
    public String type(){
        return "admin/types";
    }

}
