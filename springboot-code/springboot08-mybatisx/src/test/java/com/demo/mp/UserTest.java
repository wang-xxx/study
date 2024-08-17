package com.demo.mp;

import com.demo.mp.constants.GenderEnum;
import com.demo.mp.entity.User;
import com.demo.mp.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void saveUser() {
        User user = new User();
        // user.setId(4L);
        user.setName("辣妹子88");
        user.setAge((short) 18);
        user.setGender(GenderEnum.WOMAN);
        user.setPhone("12345678");
        userMapper.insert(user);
        System.out.println("插入成功");
    }

    @Test
    void updateUser() {
        Long id = 4L;
        User user = userMapper.selectById(id);
        user.setName("辣妹子" + new Random().nextInt(20));
        userMapper.updateById(user);
        System.out.println("更新成功");
    }

    @Test
    void deleteUserById() {
        userMapper.deleteById(4L);
        System.out.println("删除成功");
    }

    @Test
    void queryUserById() {
        User user = userMapper.selectById(4L);
    }

    @Test
    void queryUserByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 3L, 4L));
        System.out.println(users);
    }
}