package com.demo.redis.test;

import cn.hutool.json.JSONUtil;
import com.demo.redis.constants.UserGender;
import com.demo.redis.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wangxing
 * @date 2024-08-04 15:58
 */
@Slf4j
@SpringBootTest
public class RedisDataTypeTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "张三");
        log.info("name:{}", stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    void testHash() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setName("李四");
        user.setGender(UserGender.MAN);
        user.setAge((short) 16);
        user.setPhone("123456789");

        stringRedisTemplate.opsForValue().set("user", JSONUtil.toJsonStr(user));

        String str = stringRedisTemplate.opsForValue().get("user");
        User userObj = JSONUtil.toBean(str, User.class);
        log.info("用户信息转换：{}", userObj);

        stringRedisTemplate.opsForHash().put("userHash", "id", "1");
        stringRedisTemplate.opsForHash().put("userHash", "name", "李四");
        stringRedisTemplate.opsForHash().put("userHash", "gender", "MAN");
        stringRedisTemplate.opsForHash().put("userHash", "age", "16");
        stringRedisTemplate.opsForHash().put("userHash", "phone", "123456789");
        log.info("用户信息hash：{}", stringRedisTemplate.opsForHash().entries("userHash"));
    }

    @Test
    void testList() {
        String key = "list";
        for (int i = 1; i <= 5; i++) {
            stringRedisTemplate.opsForList().leftPush(key, String.valueOf(i));
        }
        log.info("list插入完毕");

        log.info("list:{}", stringRedisTemplate.opsForList().range(key, 0, -1));
    }


    @Test
    void testSet() {
        HashMap<String, User> map = new HashMap<>();
        map.put("1", new User(1L, "张三", (short) 16));
        map.put("2", new User(2L, "李四", (short) 16));
        map.put("3", new User(3L, "王五", (short) 16));
        map.put("4", new User(4L, "张三2", (short) 16));
        map.put("5", new User(5L, "张三", (short) 16));
        Iterator<Map.Entry<String, User>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, User> entry = iterator.next();
            stringRedisTemplate.opsForSet().add("set", entry.getValue().getName());
        }
        log.info("set:{}", stringRedisTemplate.opsForSet().members("set"));
    }

    @Test
    void testSetSorted() {
        HashMap<String, User> map = new HashMap<>();
        map.put("1", new User(1L, "张三", (short) 16));
        map.put("2", new User(2L, "李四", (short) 12));
        map.put("3", new User(3L, "王五", (short) 15));
        map.put("4", new User(4L, "张三2", (short) 17));
        map.put("5", new User(5L, "张三3", (short) 20));
        map.put("6", new User(6L, "张三4", (short) 21));
        map.put("7", new User(7L, "张三5", (short) 18));
        Iterator<Map.Entry<String, User>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, User> entry = iterator.next();
            stringRedisTemplate.opsForZSet().add("zset", entry.getValue().getName(), entry.getValue().getAge());
        }
        log.info("zset:{}", stringRedisTemplate.opsForZSet().reverseRange("zset", 0, 3));
    }

    @Test
    void testIncrement() {
        log.info("camera_01:{}", stringRedisTemplate.opsForValue().increment("camera_01"));
        log.info("camera_01:{}", stringRedisTemplate.opsForValue().increment("camera_01"));
        log.info("camera_01:{}", stringRedisTemplate.opsForValue().decrement("camera_01"));
    }
}
