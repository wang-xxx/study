package com.demo.anno.dao.impl;

import com.demo.anno.dao.UserDao;
import com.demo.anno.pojo.User;
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
