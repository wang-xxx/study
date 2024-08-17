package com.demo.start.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanTest implements BeanNameAware, InitializingBean, DisposableBean {

    public void test() {
        System.out.println("test");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware：" + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean：afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @Override
    public void destroy() throws Exception {

    }
}
