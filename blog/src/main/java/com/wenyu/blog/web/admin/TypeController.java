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
import javax.servlet.http.HttpServletRequest;
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

        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
       Type t = typeService.saveType(type);
       if( t== null){
           //表示添加失败
           attributes.addFlashAttribute("message", "新增失败");
       }else {
           //表示添加成功
           attributes.addFlashAttribute("message", "新增成功");
       }
        return "redirect:/admin/types";
    }

//    @PathVariable保证上下id一致
        @RequestMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        //先获取编辑的那个分类名称
        model.addAttribute("type", typeService.selectByPrimaryKey(id));
        return "admin/editType";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.selectByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
