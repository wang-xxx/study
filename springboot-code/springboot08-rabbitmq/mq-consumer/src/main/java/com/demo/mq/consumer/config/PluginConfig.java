package com.demo.mq.consumer.config;

/**
 * @author wangxing
 * @date 2024-08-04 13:36
 */
//@Configuration
public class PluginConfig {

    /*@Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_DELAY, "x-delayed-message", true, false, args);
    }*/

    /*@Bean
    public Queue orderCancelQueue() {
        return QueueBuilder.durable(QUEUE_ORDER_CANCEL).build();
    }*/

    /*@Bean
    public Binding orderBinding(CustomExchange delayExchange, Queue orderCancelQueue) {
        return BindingBuilder
                .bind(orderCancelQueue)
                .to(delayExchange)
                .with(ROUTING_KEY_ORDER_CANCEL)
                .noargs();
    }*/

}
