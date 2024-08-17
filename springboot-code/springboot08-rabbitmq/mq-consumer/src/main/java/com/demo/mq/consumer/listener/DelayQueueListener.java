package com.demo.mq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.demo.mq.common.constant.RabbitConstants.*;

/**
 * @author wangxing
 * @date 2024-08-04 14:11
 */
@Slf4j
@Component
public class DelayQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = QUEUE_ORDER_CANCEL, durable = "true"),
            exchange = @Exchange(name = EXCHANGE_DELAY, delayed = "true"),
            key = ROUTING_KEY_ORDER_CANCEL
    ))
    public void listenDirectQueue(String message) {
        log.info("消费者收到延时消息: {}", message);
    }

}
