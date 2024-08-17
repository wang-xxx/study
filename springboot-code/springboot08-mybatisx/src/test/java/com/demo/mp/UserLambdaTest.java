package com.demo.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.mp.entity.User;
import com.demo.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserLambdaTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testUserLambda() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                //.select(User::getId, User::getName, User::getAge)
                .lt(User::getAge, 18);
        List<User> list = userMapper.selectList(wrapper);
        System.out.println(list);
    }
}
