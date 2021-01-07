package com.wenyu.blog.web.admin;

import com.wenyu.blog.model.Blog;
import com.wenyu.blog.service.BLogService;
import com.wenyu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Author:wenyu
 * 2020/12/24
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Resource
    private BLogService bLogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", bLogService.listBlog(pageable, blog));
        return "admin/blogs";
    }

    //局部刷新片段
    @PostMapping("/blogs/search")
    public String searcg(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){
        model.addAttribute("page", bLogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }
}
