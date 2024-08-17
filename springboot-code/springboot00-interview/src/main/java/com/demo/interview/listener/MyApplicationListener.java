package com.demo.interview.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            //容器初始化成功
            System.out.println("MyListener：容器初始化成功");
        }
        if (event instanceof ApplicationFailedEvent) {
            //容器初始化失败
            System.out.println("MyListener：容器初始化失败");
        }
    }
}
