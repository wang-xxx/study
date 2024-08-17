package com.demo.redis.test;

import com.demo.redis.constants.RedisConstants;
import com.demo.redis.pojo.User;
import com.demo.redis.service.impl.UserServiceImpl;
import com.demo.redis.utils.RedisClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisClientTest {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void testSet() {
        redisClient.set("name:1", "张三", 10L, TimeUnit.SECONDS);
    }


    @Test
    void testLock() {
        redisClient.unLock("11111");
    }

    @Test
    void testLogicExpire() {
        Long id = 1L;
        User user = userService.getById(id);
        redisClient.setWithLogicalExpire(RedisConstants.CACHE_USER_KEY + id, user, 10L, TimeUnit.SECONDS);
    }

    @Test
    void testRedisBackup() {
        for (int i = 0; i < 100; i++) {
            redisClient.set("name:", "张三", 10L, TimeUnit.SECONDS);
        }
    }
}
