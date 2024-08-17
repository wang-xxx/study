package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.*;

@Slf4j
@SpringBootTest
public class DirectQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testDirect() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY_DIRECT1, "hello");
    }

    @Test
    void testDirect2() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY_DIRECT2, "hello");
    }

    @Test
    void testDirect3() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY_DIRECT3, "hello");
    }

    @Test
    void testDirect4() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, "abcdef", "hello");
    }

    @Test
    void testPublishFail() {
        String exchange = EXCHANGE_DIRECT;
        String routingKey = ROUTING_KEY_DIRECT1;
        String message = "hello everyone!";
        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }

}
