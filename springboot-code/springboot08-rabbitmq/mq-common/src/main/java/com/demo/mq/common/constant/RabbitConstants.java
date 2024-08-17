package com.demo.mq.common.constant;

public class RabbitConstants {


    public static final String EXCHANGE_SIMPLE = "exchange.simple";
    public static final String QUEUE_SIMPLE = "queue.simple";
    public static final String QUEUE_SIMPLE_ACK = "queue.simple.ack";

    public static final String EXCHANGE_FANOUT = "exchange.fanout";
    public static final String QUEUE_FANOUT1 = "queue.fanout";
    public static final String QUEUE_FANOUT2 = "queue.fanout2";

    public static final String EXCHANGE_DIRECT = "exchange.direct";
    public static final String QUEUE_DIRECT1 = "queue.direct";
    public static final String QUEUE_DIRECT2 = "queue.direct2";
    public static final String ROUTING_KEY_DIRECT1 = "red";
    public static final String ROUTING_KEY_DIRECT2 = "blue";
    public static final String ROUTING_KEY_DIRECT3 = "yellow";

    public static final String EXCHANGE_TOPIC = "exchange.topic";
    public static final String QUEUE_TOPIC1 = "queue.topic";
    public static final String QUEUE_TOPIC2 = "queue.topic2";
    public static final String ROUTING_KEY_TOPIC1 = "#.news";
    public static final String ROUTING_KEY_TOPIC2 = "china.#";

    public static final String EXCHANGE_DELAY = "exchange.delay";
    public static final String QUEUE_ORDER_CANCEL = "queue.order.cancel";
    public static final String ROUTING_KEY_ORDER_CANCEL = "order_cancel";
    public static final Integer ORDER_CANCEL_DELAY_MILL = 1000 * 10;

    public static final String EXCHANGE_ERROR = "exchange.error";
    public static final String QUEUE_ERROR = "queue.error";
    public static final String ROUTING_KEY_ERROR = "error";

}
