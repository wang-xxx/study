package com.demo.redis.utils;

import com.demo.redis.constants.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {

    // 开始时间戳
    private final static LocalDateTime BEGIN_TIME = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
    private final static long BEGIN_TIMESTAMP = BEGIN_TIME.toEpochSecond(ZoneOffset.UTC);

    /**
     * 序列号中：日期位数
     */
    private final static long COUNT_BITS = 32;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 唯一ID生成器
     *
     * @param keyPrefix 业务分组前缀
     * @return
     */
    public long generateId(String keyPrefix) {
        // 1.计算相差时间戳
        LocalDateTime now = LocalDateTime.now();
        long currentTimeStamp = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = currentTimeStamp - BEGIN_TIMESTAMP;

        // 2.生成序列号
        // 2.1.获取当前日期
        String dateStr = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 2.2.自增长
        String incrementKey = RedisConstants.INCREMENT_KEY + ":" + keyPrefix + ":" + dateStr;
        long count = stringRedisTemplate.opsForValue().increment(incrementKey);

        // 3.拼接并返回：32位时间戳+32为日期自增序列号
        return timestamp << COUNT_BITS | count;
    }

}
