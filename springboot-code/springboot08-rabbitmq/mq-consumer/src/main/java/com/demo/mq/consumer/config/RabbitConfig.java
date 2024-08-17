package com.demo.mq.consumer.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.demo.mq.common.constant.RabbitConstants.*;

@Component
public class RabbitConfig {

    /**
     * 交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_FANOUT, true, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT, true, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC, true, false);
    }

    /**
     * 队列
     */
    @Bean
    public Queue simpleQueue() {
        return QueueBuilder.durable(QUEUE_SIMPLE).build();
    }

    @Bean
    public Queue simpleQueueAck() {
        return QueueBuilder.durable(QUEUE_SIMPLE_ACK).build();
    }

    @Bean
    public Queue fanoutQueue() {
        return QueueBuilder.durable(QUEUE_FANOUT1).build();
    }

    @Bean
    public Queue fanoutQueue2() {
        return QueueBuilder.durable(QUEUE_FANOUT2).build();
    }

    @Bean
    public Queue directQueue() {
        return QueueBuilder.durable(QUEUE_DIRECT1).build();
    }

    @Bean
    public Queue directQueue2() {
        return QueueBuilder.durable(QUEUE_DIRECT2).build();
    }

    @Bean
    public Queue topicQueue() {
        return QueueBuilder.durable(QUEUE_TOPIC1).build();
    }

    @Bean
    public Queue topicQueue2() {
        return QueueBuilder.durable(QUEUE_TOPIC2).build();
    }

}
