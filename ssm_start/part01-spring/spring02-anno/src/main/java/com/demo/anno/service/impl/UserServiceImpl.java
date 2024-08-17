package com.demo.anno.service.impl;

import com.demo.anno.dao.UserDao;
import com.demo.anno.pojo.User;
import com.demo.anno.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /*@Autowired
    @Qualifier(value = "userDaoV2")//多个类型指定某个名称*/
    @Resource
    private UserDao userDao;

    @Override
    public List<User> showAll() {
        System.out.println("展示所有用户：UserServiceImpl.showAll()");
        return userDao.selectAll();
    }
}
