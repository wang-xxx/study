package com.demo.allcnno.dao.impl;

import com.demo.allcnno.dao.UserDao;
import com.demo.allcnno.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> selectAll() {
        System.out.println("查询所有用户：UserDaoImpl.selectAll()");
        return null;
    }

}
