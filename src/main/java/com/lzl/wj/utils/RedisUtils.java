package com.lzl.wj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class RedisUtils {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate1;

    public String getAndIncrement(String key) {
        redisTemplate.opsForValue().increment(key);
        return redisTemplate.opsForValue().get(key);
    }

    public void setUserId(String key, String userName) {
        redisTemplate.opsForValue().set(key, userName);
        redisTemplate.expire(key,60*60*24, TimeUnit.SECONDS);
    }

    public Object getAll(String key) {
        return redisTemplate1.opsForValue().get(key);
    }
    public String getAllUser(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public boolean set(String key, Object o) {
        try {
            redisTemplate1.opsForValue().set(key,o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean remove(String key) {
        Boolean delete = redisTemplate1.delete(key);
        return delete;
    }
}
