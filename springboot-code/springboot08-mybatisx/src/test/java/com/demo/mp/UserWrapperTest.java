package com.demo.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.demo.mp.entity.User;
import com.demo.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryUser() {
        // 创建查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                //.select("id", "name", "age", "gender", "phone")
                .like("name", "五")
                .ge("age", 18);
        // 查询
        List<User> list = userMapper.selectList(wrapper);
        System.out.println(list);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setAge((short) 17);
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("name", "李四");
        userMapper.update(user, wrapper);
    }

    @Test
    void testUpdateWrapper() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("age = age - 1")
                .in("id", ids);
        userMapper.update(null, wrapper);
    }
}
