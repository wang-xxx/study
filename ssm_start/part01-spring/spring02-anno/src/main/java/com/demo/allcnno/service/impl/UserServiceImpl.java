package com.demo.allcnno.service.impl;

import com.demo.allcnno.dao.UserDao;
import com.demo.allcnno.pojo.User;
import com.demo.allcnno.service.UserService;
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
