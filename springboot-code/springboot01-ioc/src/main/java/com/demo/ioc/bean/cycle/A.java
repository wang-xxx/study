package com.demo.ioc.bean.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖
 * AbstractBeanFactory.doGetBean()
 */
@Component
public class A {

    @Autowired
    private B b;

}
