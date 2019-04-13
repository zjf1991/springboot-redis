package com.yzj.springbootredis.utils;

import com.yzj.springbootredis.config.RedisKeyPrefix;
import com.yzj.springbootredis.entity.SysDept;
import com.yzj.springbootredis.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisUtilsTest {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ISysDeptService iSysDeptService;

    @Test
    public void findOne() {
        int id =34;
        String key = RedisKeyPrefix.USER + id;
        // 缓存存在
        boolean hasKey = hasKey(key);
        if (hasKey) { // 从缓存中取
            SysDept girl = (SysDept)redisTemplate.opsForValue().get(key);
            log.info("从缓存中获取了用户！");

        }
        // 从数据库取，并存回缓存
        SysDept girl = iSysDeptService.selectById(id);
        // 放入缓存，并设置缓存时间
        redisTemplate.opsForValue().set(key, girl, 600, TimeUnit.SECONDS);

    }
    public  boolean hasKey(String id) {

        // 缓存存在，删除缓存
        SysDept sysDept = (SysDept)redisTemplate.opsForValue().get(id);
        if(sysDept!=null){
            return true;
        }else {
            return  false;
        }
    }
    @Test
    public void updateUser() {
    }

    @Test
    public void deleteById() {
    }
}