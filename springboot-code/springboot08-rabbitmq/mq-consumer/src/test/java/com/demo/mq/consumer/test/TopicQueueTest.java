package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.EXCHANGE_TOPIC;

@Slf4j
@SpringBootTest
public class TopicQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testTopic() {
        String routingKey = "china.news";
        rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, routingKey, routingKey);
    }

    @Test
    void testTopic2() {
        String routingKey = "china.weather";
        rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, routingKey, routingKey);
    }

    @Test
    void testTopic3() {
        String routingKey = "japan.news";
        rabbitTemplate.convertAndSend(EXCHANGE_TOPIC, routingKey, routingKey);
    }

}
