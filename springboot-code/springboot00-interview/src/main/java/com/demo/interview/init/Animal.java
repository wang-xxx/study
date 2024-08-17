package com.demo.interview.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Animal implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Animal...InitializingBean...afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Animal...DisposableBean...destroy");
    }
}
