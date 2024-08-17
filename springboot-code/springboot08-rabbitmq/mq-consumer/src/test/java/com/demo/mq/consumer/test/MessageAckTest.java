package com.demo.mq.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.demo.mq.common.constant.RabbitConstants.QUEUE_SIMPLE_ACK;

/**
 * @author wangxing
 * @date 2024-08-04 13:20
 */
@Slf4j
@SpringBootTest
public class MessageAckTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testAck() {
        String message = "测试消息";
        String queue = QUEUE_SIMPLE_ACK;
        // 设置回调
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(result -> {
            if (result.isAck()) {
                log.info("消息发送交换机成功：ID：{}", correlationData.getId());
            } else {
                log.error("消息发送交换机失败：ID：{}，原因：{}", correlationData.getId(), correlationData.getReturnedMessage());
            }
        }, e -> {
            log.error("消息发送异常：ID：{}，原因：{}", correlationData.getId(), e.getMessage());
        });
        rabbitTemplate.convertAndSend(null, queue, message, correlationData);
    }
}
