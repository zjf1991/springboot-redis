package com.yzj.springbootredis.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yzj.springbootredis.config.RedisKeyPrefix;
import com.yzj.springbootredis.entity.SysDept;
import com.yzj.springbootredis.service.ISysDeptService;
import com.yzj.springbootredis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class SysDeptController {

    @Resource
    RedisUtils redisUtils;
    @Resource
    private ISysDeptService iSysDeptService;
    /**
     * 通过id查询，如果查询到则进行缓存
     * @param id 实体类id
     * @return 查询到的实现类
     */
    @RequestMapping("/findOne/{id}")
    public Object findOne(@PathVariable Integer id) {
        String key = RedisKeyPrefix.USER + id;
        // 缓存存在
        /*boolean hasKey = redisUtils.hasKey(key);
        if (hasKey) { // 从缓存中取
            SysDept girl = (SysDept)redisUtils.getObject(key);
            log.info("从缓存中获取了用户！");
            return girl;
        }*/
        // 从数据库取，并存回缓存
        /*SysDept girl = iSysDeptService.selectById(id);*/
        EntityWrapper<SysDept> entityWrapper=new EntityWrapper<>();
        List<SysDept> sysDepts=iSysDeptService.selectList(entityWrapper);
        // 放入缓存，并设置缓存时间
        redisUtils.setObject(key, sysDepts, 600);
        return sysDepts;
    }

    @RequestMapping("/get/{id}")
    public Object get(@PathVariable Integer id) {
        String key = RedisKeyPrefix.USER+id ;
        // 缓存存在
        /*boolean hasKey = redisUtils.hasKey(key);
        if (hasKey) { // 从缓存中取
            SysDept girl = (SysDept)redisUtils.getObject(key);
            log.info("从缓存中获取了用户！");
            return girl;
        }*/
        // 从数据库取，并存回缓存
        /*SysDept girl = iSysDeptService.selectById(id);*/

        // 放入缓存，并设置缓存时间
        List<SysDept> re=  (List<SysDept>)redisUtils.getObject(key);
        System.out.println(re);
        return re;
    }

}
