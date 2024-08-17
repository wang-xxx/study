package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.EXCHANGE_FANOUT;

@Slf4j
@SpringBootTest
public class FanoutQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testFanout() {
        String exchange = EXCHANGE_FANOUT;
        String message = "hello everyone!";
        rabbitTemplate.convertAndSend(exchange, null, message);
    }
}
