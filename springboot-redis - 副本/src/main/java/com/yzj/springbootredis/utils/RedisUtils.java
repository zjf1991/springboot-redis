package com.yzj.springbootredis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 单机版redis工具类
 */
@Slf4j
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 设置缓存
     * @param key    缓存key
     * @param value  缓存value
     */
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
        log.debug("RedisUtil:set cache key={},value={}", key, value);
    }

    /**
     * 设置缓存，并且自己指定过期时间
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setString( String key, String value, int expireTime) {
        redisTemplate.opsForValue().set(key,value,expireTime,TimeUnit.SECONDS);
        log.debug("RedisUtil:set cache key={},value={}", key, value,expireTime,TimeUnit.SECONDS);
    }
    /**
     * 设置缓存对象
     * @param key    缓存key
     * @param obj  缓存value
     */
    public <T> void setObject(String key, T obj ) {
        redisTemplate.opsForValue().set(key,obj);
    }
    /**
     * 设置缓存对象
     * @param key    缓存key
     * @param obj  缓存value
     */
    public <T> void setObject(String key, T obj , int expireTime) {
        redisTemplate.opsForValue().set(key,obj,expireTime,TimeUnit.SECONDS);
    }

    /**
     * 获取指定key的缓存
     * @param key---JSON.parseObject(value, User.class);
     */
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断当前key值 是否存在
     *
     * @param key
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 获取指定key的缓存
     * @param key
     */
    public String getString(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        log.debug("RedisUtil:get cache key={},value={}",key, value);
        return value;
    }

    /**
     * 删除指定key的缓存
     * @param key
     */
    public void delete(String from,String key) {
        redisTemplate.delete(key);
        log.info("RedisUtil:delete "+from+" cache key={}", key);
    }

    /**
     * @Title: expire
     * @Description: 更新key的失效时间
     * @param key
     * @throws
     */
    public Boolean expire(String key,int seconds) {
        log.debug("RedisUtil:expire cache key={}", key);
        return redisTemplate.expire(key, seconds,TimeUnit.SECONDS);
    }

    public Boolean pexpire(String key,long seconds) {
        log.debug("RedisUtil:expire cache key={}", key);
        return redisTemplate.expire(key, seconds,TimeUnit.SECONDS);
    }


}
