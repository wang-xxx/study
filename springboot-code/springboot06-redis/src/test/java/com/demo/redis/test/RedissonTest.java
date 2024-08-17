package com.demo.redis.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock:order:" + 1);
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                log.info("获取锁成功，开始业务处理");
                Thread.sleep(20000);
            } finally {
                log.info("业务处理完毕，释放锁");
                lock.unlock();
            }
        }
    }

    @Test
    void testLockRetry() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock:order:" + 1);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                if (lock.tryLock()) {
                    try {
                        log.info("获取锁成功，开始业务处理");
                        Thread.sleep(20000);
                    } finally {
                        log.info("业务处理完毕，释放锁");
                        lock.unlock();
                    }
                }
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1").start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                if (lock.tryLock(60, 10, TimeUnit.SECONDS)) {
                    try {
                        log.info("获取锁成功，开始业务处理");
                    } finally {
                        log.info("业务处理完毕，释放锁");
                        lock.unlock();
                    }
                }
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t2").start();
        countDownLatch.await();
    }

    @Test
    void testReentrantLock() {
        RLock lock = redissonClient.getLock("lock");
    }
}
