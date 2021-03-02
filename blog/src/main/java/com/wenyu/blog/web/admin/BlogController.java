package com.wenyu.blog.web.admin;

import com.wenyu.blog.dao.BlogRepository;
import com.wenyu.blog.model.Blog;
import com.wenyu.blog.model.Tag;
import com.wenyu.blog.model.User;
import com.wenyu.blog.service.BLogService;
import com.wenyu.blog.service.TagService;
import com.wenyu.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Author:wenyu
 * 2020/12/24
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Resource
    private BLogService blogService;


    @Autowired
    private TypeService typeService;
    @Resource
    private TagService tagService;


    @RequestMapping("/blogs")
    public String blogs(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));

        return LIST;
    }

    //局部刷新片段
    @PostMapping("/blogs/search")
    public String searcg(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model){
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @RequestMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("types", typeService.listType());
    }

    @RequestMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Optional<Blog> blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);

        return "admin/blogs-edit";
    }

    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id){
       blogService.deleteBlog(id);

        return "redirect:/admin/blogs";
    }


    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        //blog.setTags(tagService.getTag(blog.getTag().getId()));
        //blog.setTag(tagService.getOneTag(blog.getTag().getId()));
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

}
