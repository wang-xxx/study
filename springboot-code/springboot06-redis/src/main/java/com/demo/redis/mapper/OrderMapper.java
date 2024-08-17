package com.demo.redis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.redis.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
