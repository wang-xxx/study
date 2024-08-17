package com.demo.ioc.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2024-07-19 14:56
 */
@Slf4j
@Data
@Component
public class TeddyDog extends Dog implements InitializingBean, BeanNameAware {

    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean回调方法");
    }

    @Override
    public void setBeanName(String name) {
        log.info("BeanNameAware回调方法");
    }
}
