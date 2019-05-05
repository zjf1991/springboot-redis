package com.yzj.springboot2.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yzj.springboot2.entity.SysDept;
import com.yzj.springboot2.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysDeptController {

    @Autowired
    private ISysDeptService iSysDeptService;

    @RequestMapping("/get")
    public Object getDept() {

        EntityWrapper<SysDept> entityWrapper = new EntityWrapper<>();
        //分页查询
        Page<SysDept> list = iSysDeptService.selectPage(new Page<>(1, 6), entityWrapper);
        return list;
    }

    //事务
    @Transactional
    @RequestMapping("/ex/{i}")
    public void getex(@PathVariable int i) {
        SysDept sysDept = iSysDeptService.selectById(42);
        sysDept.setFullname("广东源自家");
        sysDept.updateById();
        int j = 1 / i;
    }
}
