package com.demo.ioc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wangxing
 * @date 2024-07-21 11:52
 */
@Slf4j
public class TestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器生效：{}");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
