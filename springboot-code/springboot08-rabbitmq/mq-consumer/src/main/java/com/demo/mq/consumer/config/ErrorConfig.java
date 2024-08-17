package com.demo.mq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.demo.mq.common.constant.RabbitConstants.*;

/**
 * @author wangxing
 * @date 2024-08-04 15:12
 */
@Configuration
@ConditionalOnProperty(name = "spring.rabbitmq.listener.simple.retry.enabled", havingValue = "true")
public class ErrorConfig {

    @Bean
    public DirectExchange errorExchange() {
        return new DirectExchange(EXCHANGE_ERROR, true, false);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(QUEUE_ERROR, true);
    }

    @Bean
    public Binding bindingError(DirectExchange errorExchange, Queue errorQueue) {
        return BindingBuilder.bind(errorQueue)
                .to(errorExchange)
                .with(ROUTING_KEY_ERROR);
    }

    @Bean
    public MessageRecoverer errorMessageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, EXCHANGE_ERROR, ROUTING_KEY_ERROR);
    }

}
