package com.demo.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
