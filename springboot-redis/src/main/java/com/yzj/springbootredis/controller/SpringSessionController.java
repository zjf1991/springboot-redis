package com.yzj.springbootredis.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SpringSessionController {


    @RequestMapping("/web/showsession")
    public Object get(HttpServletRequest request) {

        return request.getSession().getId();
    }

}
