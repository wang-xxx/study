package com.demo.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.demo.mq.common.constant.RabbitConstants.*;

@Slf4j
@Component
public class SimpleQueueListener {

    @RabbitListener(queues = QUEUE_SIMPLE)
    public void listenSimpleQueue(String message) {
        log.info("收到消息: {}", message);
    }

    @RabbitListener(queues = QUEUE_SIMPLE_ACK)
    public void listenSimpleQueueAck(String message) {
        log.info("ack收到消息: {}", message);
        // int i = 10 / 0;
        log.info("ack处理消息完成");
    }

}
