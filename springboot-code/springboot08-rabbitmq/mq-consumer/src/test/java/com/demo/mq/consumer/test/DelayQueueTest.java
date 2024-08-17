package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.demo.mq.common.constant.RabbitConstants.*;

/**
 * @author wangxing
 * @date 2024-08-04 14:14
 */
@Slf4j
@SpringBootTest
public class DelayQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void publishDelayMsg() {
        rabbitTemplate.convertAndSend(EXCHANGE_DELAY, ROUTING_KEY_ORDER_CANCEL, "这是一条延时消息", message -> {
            message.getMessageProperties().setDelay(ORDER_CANCEL_DELAY_MILL);
            return message;
        });
        log.info("延时消息发送成功");
    }
}
