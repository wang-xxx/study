package com.demo.redis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testString() {
        // 测试redisTemplate
        redisTemplate.opsForValue().set("name", "法外狂徒张三");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testObject() {
        // 测试redisTemplate
        // redisTemplate.opsForValue().set("user", new User("张三", 18));
        // User user = (User) redisTemplate.opsForValue().get("user");
        // System.out.println(user);
    }

}
