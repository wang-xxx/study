package com.demo.mq.consumer.listener;


import com.demo.mq.common.constant.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.demo.mq.common.constant.RabbitConstants.*;

@Slf4j
@Component
public class DirectQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(QUEUE_DIRECT1),
            exchange = @Exchange(name = EXCHANGE_DIRECT),
            key = {ROUTING_KEY_DIRECT1, ROUTING_KEY_DIRECT3}
    ))
    public void listenDirectQueue(String message) {
        log.info("消费者1收到消息: {}", message);
        // throw new RuntimeException("故意抛出异常");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(QUEUE_DIRECT2),
            exchange = @Exchange(name = EXCHANGE_DIRECT),
            key = {ROUTING_KEY_DIRECT2, ROUTING_KEY_DIRECT3}
    ))
    @RabbitListener(queues = RabbitConstants.QUEUE_DIRECT2)
    public void listenDirectQueue2(String message) {
        log.info("消费者2收到消息: {}", message);
    }

}
