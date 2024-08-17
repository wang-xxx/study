package com.demo.result.test;

import com.demo.result.mapper.OrderMapper;
import com.demo.result.pojo.Order;
import com.demo.result.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

@Slf4j
public class OrderTest {

    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        Order order = new Order(null, "这是一个订单");
        mapper.insertOrder(order);
        log.info("{}", order);

        sqlSession.commit();
        sqlSession.close();
    }

}
