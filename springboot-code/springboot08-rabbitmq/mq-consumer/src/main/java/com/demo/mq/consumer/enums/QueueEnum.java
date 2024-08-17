package com.demo.mq.consumer.enums;

/**
 * @author wangxing
 * @date 2024-08-04 13:46
 */
public enum QueueEnum {

    QUEUE_SIMPLE(null, "queue.simple", null),
    QUEUE_SIMPLE_ACK(null, "queue.simple.ack", null),

    QUEUE_FANOUT1("exchange.fanout", "queue.fanout", null),
    QUEUE_FANOUT2("exchange.fanout", "queue.fanout2", null),

    QUEUE_DIRECT1("exchange.direct", "queue.direct", "red", "yellow"),
    QUEUE_DIRECT2("exchange.direct", "queue.direct2", "blue", "yellow"),

    QUEUE_TOPIC1("exchange.topic", "queue.topic", "#.news"),
    QUEUE_TOPIC2("exchange.topic", "queue.topic", "china.#"),


    QUEUE_ORDER_CANCEL("exchange.delay", "queue.order.cancel", "queue.order.cancel"),

    ;

    /**
     * 交换机
     */
    private final String exchange;
    /**
     * 队列
     */
    private final String queue;
    /**
     * 路由键
     */
    private final String[] routeKeyArr;

    QueueEnum(String exchange, String queue, String... routeKeyArr) {
        this.exchange = exchange;
        this.queue = queue;
        this.routeKeyArr = routeKeyArr;
    }

    public String getExchange() {
        return exchange;
    }

    public String getQueue() {
        return queue;
    }

    public String getRouteKey() {
        return routeKeyArr != null && routeKeyArr.length > 0 ? routeKeyArr[0] : null;
    }

    public String[] getRouteKeyArr() {
        return routeKeyArr;
    }
}
