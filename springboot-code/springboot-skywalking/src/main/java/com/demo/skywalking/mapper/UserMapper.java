package com.demo.skywalking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.skywalking.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
