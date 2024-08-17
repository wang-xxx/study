package com.arthas.out.memory.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author wangxing
 * @date 2024-07-22 13:18
 */
@Slf4j
@Component
public class ScheduleTask {

    private int count = 0;
    private final ArrayList<Object> list = new ArrayList();

    @Scheduled(fixedRate = 1000L)
    public void test() {
        log.info("定时任务调用:{}", ++count);
        list.add(new InnerClass());
    }

    class InnerClass {
        private final byte[] bytes = new byte[1024 * 1024 * 10];
    }

}
