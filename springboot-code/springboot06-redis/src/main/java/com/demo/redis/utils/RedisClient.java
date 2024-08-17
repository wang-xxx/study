package com.demo.redis.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.demo.redis.constants.RedisConstants;
import com.demo.redis.pojo.RedisData;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class RedisClient {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 线程池：缓存重建
     */
    private final static ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     * @param time  过期时间
     * @param unit  时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /**
     * 设置缓存-逻辑过期
     *
     * @param key   缓存key
     * @param value 缓存value
     * @param time  逻辑过期时间
     * @param unit  时间单位
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        // 设置过期时间
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 缓存穿透查询
     *
     * @param keyPrefix  key分组前缀
     * @param id         缓存id
     * @param type       返回结果类型
     * @param dbCallback 数据库回调
     * @param time       过期时间
     * @param unit       时间单位
     * @param <R>        返回结果
     * @param <ID>       id
     * @return
     */
    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbCallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 1.查询缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        // 2.是否存在
        if (StrUtil.isNotBlank(json)) {
            // 3.存在直接返回
            return JSONUtil.toBean(json, type);
        }
        //判断是否为空
        if (json != null) {
            return null;
        }

        //不存在，根据id查询数据库
        R result = dbCallback.apply(id);
        //不存在返回错误
        if (result == null) {
            //写入空值
            stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.SECONDS);
            return null;
        }
        //存在，写入redis
        this.set(key, JSONUtil.toJsonStr(result), time, unit);
        return result;
    }

    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbCallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 1.从Redis查缓存
        String userJson = stringRedisTemplate.opsForValue().get(key);
        // 2.缓存是否命中
        if (StrUtil.isEmpty(userJson)) {
            // 3.直接返回
            return null;
        }
        // 4.缓存命中
        RedisData redisData = JSONUtil.toBean(userJson, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5.判断是否过期
        // 5.1 未过期，直接返回
        if (expireTime.isAfter(LocalDateTime.now())) {
            return r;
        }
        // 5.2 已过期，需要缓存重建
        // 6.缓存重建
        String lockKey = RedisConstants.LOCK_USER_KEY + id;
        // 6.1 获取互斥锁
        // 6.2 判断锁获取是否成功
        if (tryLock(lockKey)) {
            // 6.3 成功，开启独立线程，实现缓存重建
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    // 重建缓存
                    // 查询数据库
                    R r1 = dbCallback.apply(id);
                    // 写入Redis
                    this.setWithLogicalExpire(key, r1, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unLock(lockKey);
                }
            });
        }
        // 6.4 失败，返回过期的数据
        return r;
    }

    public boolean tryLock(String lockKey) {
        Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "", RedisConstants.LOCK_USER_TTL, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(ifAbsent);
    }

    public void unLock(String lockKey) {
        stringRedisTemplate.delete(lockKey);
    }

}
