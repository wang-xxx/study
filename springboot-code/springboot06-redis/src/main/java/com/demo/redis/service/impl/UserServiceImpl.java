package com.demo.redis.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.redis.constants.RedisConstants;
import com.demo.redis.mapper.UserMapper;
import com.demo.redis.pojo.RedisData;
import com.demo.redis.pojo.User;
import com.demo.redis.service.UserService;
import com.demo.redis.utils.RedisClient;
import com.demo.redis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisClient redisClient;

    private final static ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    @Override
    public List<User> list() {
        return null;
    }

    public User getById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Result getUserById(Long id) {
        //User user = queryWithThrough(id);
        //User user = queryWithMutex(id);
        //User user = queryWithLogicalExpire(id);

        // 解决缓存穿透
        //User user = redisClient.queryWithPassThrough(CACHE_USER_KEY, id, User.class, this::getById, CACHE_USER_TTL, TimeUnit.MINUTES);

        // 解决缓存穿透：逻辑过期
        User user = redisClient.queryWithLogicalExpire(RedisConstants.CACHE_USER_KEY, id, User.class, this::getById, 10L, TimeUnit.MINUTES);

        if (user == null) {
            Result.fail("用户不存在");
        }
        return Result.ok(user);
    }

    /**
     * 缓存穿透：缓存null数据
     */
    public User queryWithThrough(Long id) {
        String key = RedisConstants.CACHE_USER_KEY + id;
        // 1.从Redis查缓存
        String userJson = stringRedisTemplate.opsForValue().get(key);
        // 2.缓存命中，直接返回
        if (StrUtil.isNotBlank(userJson)) {
            return JSONUtil.toBean(userJson, User.class);
        }
        // 3.缓存未命中，查询数据库
        if (userJson != null) {
            return null;
        }
        // 4.不存在，查询数据库
        User user = getById(id);
        // 5.数据库不存在
        if (user == null) {
            //未命中：写入空缓存
            stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.SECONDS);
            return null;
        }
        // 6.写入缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(user), RedisConstants.CACHE_USER_TTL, TimeUnit.SECONDS);
        return user;
    }

    /**
     * 缓存穿透：利用互斥锁
     */
    public User queryWithMutex(Long id) {
        String key = RedisConstants.CACHE_USER_KEY + id;
        String lockKey = RedisConstants.LOCK_USER_KEY + id;
        User user = null;
        try {
            // 1.从Redis查缓存
            String userJson = stringRedisTemplate.opsForValue().get(key);
            // 2.缓存命中，直接返回
            if (StrUtil.isNotBlank(userJson)) {
                return JSONUtil.toBean(userJson, User.class);
            }
            // 3.缓存未命中，查询数据库
            if (userJson != null) {
                return null;
            }
            // 4.不存在，查询数据库
            // 4.1 获取互斥锁
            boolean isLock = redisClient.tryLock(lockKey);
            // 4.2 判读锁获取是否成功
            if (!isLock) {
                // 4.3 失败休眠递归重试
                Thread.sleep(50);
                return queryWithMutex(id);
            }
            // 4.4 获取锁成功，再次检测缓存是否存在
            userJson = stringRedisTemplate.opsForValue().get(key);
            if (StrUtil.isNotBlank(userJson)) {
                return JSONUtil.toBean(userJson, User.class);
            }
            user = getById(id);
            //休眠检验并发缓存重建
            //Thread.sleep(200);
            // 5.数据库不存在
            if (user == null) {
                //未命中：写入空缓存
                stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.SECONDS);
                return null;
            }
            // 6.写入缓存
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(user), RedisConstants.CACHE_USER_TTL, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 7.释放锁
            redisClient.unLock(lockKey);
        }
        return user;
    }

    /**
     * 缓存穿透：设置数据逻辑过期时间
     */
    public User queryWithLogicalExpire(Long id) {
        String key = RedisConstants.CACHE_USER_KEY + id;
        // 1.从Redis查缓存
        String userJson = stringRedisTemplate.opsForValue().get(key);
        // 2.缓存是否命中
        if (StrUtil.isEmpty(userJson)) {
            // 3.直接返回
            return null;
        }
        // 4.缓存命中
        RedisData redisData = JSONUtil.toBean(userJson, RedisData.class);
        User user = JSONUtil.toBean((JSONObject) redisData.getData(), User.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5.判断是否过期
        // 5.1 未过期，直接返回
        if (expireTime.isAfter(LocalDateTime.now())) {
            return user;
        }
        // 5.2 已过期，需要缓存重建
        // 6.缓存重建
        String lockKey = RedisConstants.LOCK_USER_KEY + id;
        // 6.1 获取互斥锁
        // 6.2 判断锁获取是否成功
        if (redisClient.tryLock(lockKey)) {
            // 6.3 成功，开启独立线程，实现缓存重建
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    // 重建缓存
                    saveWithLogicalExpire(id, 20L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    redisClient.unLock(lockKey);
                }
            });
        }
        // 6.4 失败，返回过期的数据
        return user;
    }

    public void saveWithLogicalExpire(Long id, Long expireSeconds) {
        // 1.查询数据
        User user = getById(id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 2.封装逻辑过期时间
        RedisData redisData = new RedisData();
        redisData.setData(user);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));
        // 3.写入Redis
        stringRedisTemplate.opsForValue().set(RedisConstants.CACHE_USER_KEY + id, JSONUtil.toJsonStr(redisData));

    }
}