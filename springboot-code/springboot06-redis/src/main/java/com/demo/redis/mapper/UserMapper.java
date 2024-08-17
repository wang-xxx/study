package com.demo.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.redis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where id = #{id}")
    User getUserById(Long id);

}
