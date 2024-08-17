package com.demo.ioc.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2024-08-11 14:55
 */
@Slf4j
@Component
public class TimerTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void timer() {
        log.info("定时任务执行");
    }

}
