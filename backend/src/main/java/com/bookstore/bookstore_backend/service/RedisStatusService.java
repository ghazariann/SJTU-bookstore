package com.bookstore.bookstore_backend.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisStatusService {

    private final RedisTemplate<String, String> redisTemplate;
    private boolean isRedisAvailable = true;

    public RedisStatusService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        checkRedis();
        System.out.println("Redis is available: " + isRedisAvailable);
    }

    private void checkRedis() {
        try {
            redisTemplate.getConnectionFactory().getConnection().ping();
        } catch (Exception e) {
            isRedisAvailable = false;
        }
    }

    public boolean isRedisAvailable() {
        return isRedisAvailable;
    }
}
