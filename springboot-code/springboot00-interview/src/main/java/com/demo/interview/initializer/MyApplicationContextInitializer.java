package com.demo.interview.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //给ApplicationContext注入环境属性
        //1.准备属性
        Map<String, Object> evnMap = new HashMap<>();
        evnMap.put("applicationName", "bigEvent");

        //2.获取属性资源管理对象
        //获取环境对象
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //属性资源管理对象
        MutablePropertySources propertySources = environment.getPropertySources();

        //3.注册
        propertySources.addLast(new MapPropertySource("evnMap", evnMap));
    }
}
