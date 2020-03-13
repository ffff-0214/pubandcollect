package edu.qingtai.pubandcollect.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisUtils(final StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    public String get(final String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(final String key, final String value, long i, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, value, i, timeUnit);
    }

    public boolean delete(final String key){
        return stringRedisTemplate.delete(key);
    }
}
