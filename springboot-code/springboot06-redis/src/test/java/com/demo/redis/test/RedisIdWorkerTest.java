package com.demo.redis.test;

import com.demo.redis.utils.RedisIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class RedisIdWorkerTest {

    private final ExecutorService executorService = Executors.newFixedThreadPool(500);

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Test
    void testIdWorker() throws InterruptedException {
        final int count = 500;
        CountDownLatch latch = new CountDownLatch(count);
        Runnable t = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.generateId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            executorService.submit(t);
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - begin));
    }
}