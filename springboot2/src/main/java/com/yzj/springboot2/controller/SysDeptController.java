package com.yzj.springboot2.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yzj.springboot2.entity.SysDept;
import com.yzj.springboot2.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return iSysDeptService.selectList(entityWrapper);
    }

    @RequestMapping("/ex/{i}")
    public void getex(@PathVariable int i) {
        int j=1/i;
    }
}
