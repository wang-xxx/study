package com.demo.ioc.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2024-07-27 19:04
 */
@Slf4j
@Component
public class IOCEventListener {

    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("事件监听{}", event);
    }

}
