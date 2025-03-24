package com.backend.utils;

import com.backend.config.JsonRedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil<V> {

    @Autowired
    JsonRedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("Redis set error: {}", e.getMessage());
            return false;
        }
    }

    public boolean setex(String key, V value, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
                return true;
            } else {
                return set(key, value);
            }

        } catch (Exception e) {
            logger.error("Redis setex error: {}", e.getMessage());
            return false;
        }
    }

}
