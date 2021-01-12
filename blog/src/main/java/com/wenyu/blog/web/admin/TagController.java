package com.wenyu.blog.web.admin;

import com.wenyu.blog.model.Tag;
import com.wenyu.blog.model.Type;
import com.wenyu.blog.service.TagService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.validation.Valid;

/**
 * Author:wenyu
 * 2021/1/6
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @RequestMapping("/tags")
    public String tag(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.ASC)
                      Pageable pageable, Model model)
    {
        model.addAttribute("page", tagService.tagList(pageable));

        return "admin/tags";
    }

    @RequestMapping("/tags/input")
    public String addTag(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String input(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.selectByName(tag.getName());
        if(tag1 != null){
            //说明有这个类型
            result.rejectValue("name","nameError","不能添加重复的标签");

        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }
        Tag t = tagService.saveTag(tag);
        if( t== null){
            //表示添加失败
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            //表示添加成功
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.selectByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }


        Tag t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    //编辑
    @RequestMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag", tagService.selectByPrimaryKey(id));
        return "admin/editTag";
    }





    //删除
    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "标签删除成功");
        return "redirect:/admin/tags";
    }
}
