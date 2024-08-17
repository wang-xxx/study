package com.demo.interview.definition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public String abc() {
        return "bean分类";
    }

}
