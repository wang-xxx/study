package com.demo.mp;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.demo.mp.entity.User;
import com.demo.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DynamicSqlTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testDynamicSql() {
        //更新条件
        List<Integer> ids = Arrays.asList(1, 2, 3, 4);
        //定义条件
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .set("age", 1)
                .in("id", ids);
        //更新
        userMapper.update(wrapper);
    }

}
