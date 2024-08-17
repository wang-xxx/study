package com.demo.common.interceptor;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxing
 * @date 2024-07-17 08:57
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ThreadLocal存储用户信息
        String userInfo = request.getHeader("userInfo");
        if (StrUtil.isNotBlank(userInfo)) {
            // TODO 将用户信息存入ThreadLocal
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO 清除ThreadLocal中的用户信息
    }
}
