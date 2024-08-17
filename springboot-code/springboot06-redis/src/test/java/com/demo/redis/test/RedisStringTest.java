package com.demo.redis.test;

import com.demo.redis.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisStringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testObject() throws JsonProcessingException {
        User user = new User();
        user.setName("李四");
        user.setAge((short) 16);
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user", json);

        String userJson = stringRedisTemplate.opsForValue().get("user");
        User user1 = mapper.readValue(userJson, User.class);
        System.out.println(user1);
    }

}
