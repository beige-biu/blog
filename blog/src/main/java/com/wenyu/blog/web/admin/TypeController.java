package com.wenyu.blog.web.admin;


import com.wenyu.blog.model.Type;
import com.wenyu.blog.service.TypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Author:wenyu
 * 2020/12/28
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("/types")
    public String type(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.ASC)
                               Pageable pageable,Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String addType(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @PostMapping("types")
    //@Valid 需要校验的类
    public String input(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.selectByName(type.getName());
        if(type1 != null){
            //说明有这个类型
            result.rejectValue("name","nameError","不能添加重复的分类");
            //attributes.addFlashAttribute("message", "已经有这个分类");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
       Type t = typeService.saveType(type);
       if( t== null){
           //表示添加失败
           attributes.addFlashAttribute("message", "操作失败");
       }else {
           //表示添加成功
           attributes.addFlashAttribute("message", "操作成功");
       }
        return "redirect:/admin/types";
    }

//    @PathVariable保证上下id一致
        @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        //先获取编辑的那个分类名称
            String name = typeService.selectByPrimaryKey(id).getName();
        model.addAttribute("type", name);
        return "admin/type-input";
    }

}
