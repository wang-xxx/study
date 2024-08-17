package com.demo.ioc.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2024-07-20 09:23
 */
@Slf4j
@Component
public class CustomListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("容器已经启动，我们开始自定义操作");
    }

}