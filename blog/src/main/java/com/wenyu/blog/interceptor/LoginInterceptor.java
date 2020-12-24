package com.wenyu.blog.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:wenyu
 * 2020/12/24
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //说明用户为空，即没有登录
        if(request.getSession().getAttribute("user")==null){
            //重定向到登录页面
            response.sendRedirect("/admin");
            return false;
        }
        return true;

    }
}
