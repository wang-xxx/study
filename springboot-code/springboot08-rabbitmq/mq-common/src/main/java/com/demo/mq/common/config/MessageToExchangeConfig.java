package com.demo.mq.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageToExchangeConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("消息发送至交换机失败：{}，失败原因：{}", correlationData, cause);
                // TODO 消息至交换机失败处理
            }
        });
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            MessageProperties messageProperties = returnedMessage.getMessage().getMessageProperties();
            // 判断是否为延时消息
            Integer delay = messageProperties.getReceivedDelay();
            if (delay != null && delay > 0) {
                return;
            }
            log.error("交换机路由消息至队列失败：响应码：{}，失败原因：{}，交换机：{}，路由key：{}，消息：{}",
                    returnedMessage.getReplyCode(),
                    returnedMessage.getReplyText(),
                    returnedMessage.getExchange(),
                    returnedMessage.getRoutingKey(),
                    returnedMessage.getMessage());
            // TODO 交换机路由消息至队列失败处理
        });
    }

}
