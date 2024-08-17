package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.QUEUE_SIMPLE;

@Slf4j
@SpringBootTest
public class SimpleQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSimple() {
        String queue = QUEUE_SIMPLE;
        String message = "hello everyone!";
        rabbitTemplate.convertAndSend(queue, message);
    }

}
