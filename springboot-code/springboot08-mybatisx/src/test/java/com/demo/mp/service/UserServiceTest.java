package com.demo.mp.service;

import com.demo.mp.constants.GenderEnum;
import com.demo.mp.entity.User;
import com.demo.mp.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void queryUser() {
        User user = userService.getById(1L);
        System.out.println(user);
    }

    @Test
    void insertUser() {
        User user = new User(null, "飞飞", (short) 18, GenderEnum.MAN, "13800000002", null);
        userService.save(user);
    }

    @Test
    void testQuery() {
        List<User> list = userService.listByIds(Arrays.asList(1, 3, 12));
        System.out.println(list);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(4L);
        UserInfo userinfo = new UserInfo();
        userinfo.setProvince("湖北");
        userinfo.setCity("荆州");
        userinfo.setCounty("石首");
        user.setInfo(userinfo);
        userService.updateById(user);
    }
}