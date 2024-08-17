package com.demo.other.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中user登录信息
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            //登陆了
            return true;
        } else {
            //未登录
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("请先登录，再访问！<a href='/public/resource/login'>点击此处登录</a>");
            return false;
        }
    }
}
