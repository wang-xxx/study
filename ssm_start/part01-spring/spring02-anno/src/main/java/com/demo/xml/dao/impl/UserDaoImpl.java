package com.demo.xml.dao.impl;

import com.demo.xml.dao.UserDao;
import com.demo.xml.pojo.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> selectAll() {
        System.out.println("查询所有用户：UserDaoImpl.selectAll()");
        return null;
    }

}
