package com.demo.redis.test;

import com.demo.redis.utils.SimpleRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangxing
 * @date 2024-07-25 16:44
 */
@Slf4j
@SpringBootTest
public class ThreadPoolTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOCK_KEY = "lock:";
    private static final String PREFIX = "pool:";

    @Test
    void testPool() throws InterruptedException {
        // 创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                100,
                200,
                60,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new CustomizableThreadFactory("netty-pool-main-thread"));
        // 设置拒绝策略
        pool.setRejectedExecutionHandler((r, executor) -> {
            try {
                Thread extThread = new Thread(r, "netty-pool-ext-thread");
                extThread.start();
            } catch (Exception e) {
                log.error("线程池额外线程发生异常", e);
            }
        });
        // 先删除
        Set<String> keys = stringRedisTemplate.keys(PREFIX + "*");
        stringRedisTemplate.delete(keys);
        // 提交线程任务
        ConcurrentHashMap<Integer, AtomicInteger> map = new ConcurrentHashMap<>();
        int num = 0;
        for (int i = 1; i <= 100; i++) {
            num++;
            int j = i;
            int key = j % 5;
            pool.submit(() -> {
                SimpleRedisLock redisLock = new SimpleRedisLock(LOCK_KEY + key, stringRedisTemplate);
                boolean tryLock = redisLock.tryLock(20000);
                try {
                    if (tryLock) {
                        log.info("获取到锁，i:{}，key:{}", j, key);
                        // 计数
                        AtomicInteger count = map.get(key);
                        if (count == null) {
                            count = new AtomicInteger(0);
                        }
                        count.incrementAndGet();
                        map.put(key, count);
                        // 更新值
                        stringRedisTemplate.opsForValue().increment(PREFIX + key);
                    }
                } finally {
                    redisLock.unlock();
                }
            });
            Thread.sleep(10);
        }
        log.info("计数：{}，{}", num, map);
        pool.shutdown();
    }
}
