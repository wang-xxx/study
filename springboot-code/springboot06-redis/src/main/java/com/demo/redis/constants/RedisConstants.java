package com.demo.redis.constants;

public class RedisConstants {

    public static final Long CACHE_NULL_TTL = 30L;

    public static final String LOCK_USER_KEY = "lock:user";
    public static final Long LOCK_USER_TTL = 10L;

    public static final String INCREMENT_KEY = "incr:";


    public static final String CACHE_USER_KEY = "user:";
    public static final Long CACHE_USER_TTL = 60 * 10L;

    public static final String CACHE_ORDER_KEY = "lock:";
    public static final Long LOCK_ORDER_TTL = 5L;
}
