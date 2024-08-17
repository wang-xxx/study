package com.demo.allcnno.dao.impl;

import com.demo.allcnno.dao.UserDao;
import com.demo.allcnno.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoV2")
public class UserDaoV2Impl implements UserDao {

    @Override
    public List<User> selectAll() {
        System.out.println("查询所有用户：UserDaoV2Impl.selectAll()");
        return null;
    }

}
