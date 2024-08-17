package com.demo.mq.consumer.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.demo.mq.common.constant.RabbitConstants.*;

@Slf4j
@Component
public class FanoutQueueListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(QUEUE_FANOUT1),
            exchange = @Exchange(name = EXCHANGE_FANOUT, type = ExchangeTypes.FANOUT)
    ))
    public void listenFanoutQueue(String message) {
        log.info("消费者1收到消息: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(QUEUE_FANOUT2),
            exchange = @Exchange(name = EXCHANGE_FANOUT, type = ExchangeTypes.FANOUT)
    ))
    public void listenFanoutQueue2(String message) {
        log.info("消费者2收到消息: {}", message);
    }

}
