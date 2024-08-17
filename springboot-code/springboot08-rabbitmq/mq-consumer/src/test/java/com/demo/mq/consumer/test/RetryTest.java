package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.*;

/**
 * @author wangxing
 * @date 2024-08-04 15:27
 */
@Slf4j
@SpringBootTest
public class RetryTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void retry() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY_DIRECT1, "hello");
    }
}
