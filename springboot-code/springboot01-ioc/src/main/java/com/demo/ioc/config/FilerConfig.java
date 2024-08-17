package com.demo.ioc.config;

import com.demo.ioc.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author wangxing
 * @date 2024-07-21 12:05
 */
@Configuration
public class FilerConfig {

    @Bean
    public FilterRegistrationBean testFilter() {
        FilterRegistrationBean<Filter> register = new FilterRegistrationBean<>();
        register.setFilter(new TestFilter());
        register.addUrlPatterns("/*");
        register.setName("testFilter");
        return register;
    }

}
