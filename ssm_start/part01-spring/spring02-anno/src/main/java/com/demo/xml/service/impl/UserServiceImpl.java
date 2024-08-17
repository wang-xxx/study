package com.demo.xml.service.impl;

import com.demo.xml.dao.UserDao;
import com.demo.xml.pojo.User;
import com.demo.xml.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public List<User> showAll() {
        System.out.println("展示所有用户：UserServiceImpl.showAll()");
        return userDao.selectAll();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
