package com.demo.redis.test;

import com.demo.redis.utils.SimpleRedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class SimpleRedisLockTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Long USER_ID = 1L;

    @Test
    void testTryLock() {
        SimpleRedisLock simpleRedisLock = new SimpleRedisLock("order:" + USER_ID, stringRedisTemplate);
        simpleRedisLock.tryLock(10);
    }

    @Test
    void testUnLock() {
        SimpleRedisLock simpleRedisLock = new SimpleRedisLock("order:" + USER_ID, stringRedisTemplate);
        simpleRedisLock.unlock();
    }

}
