package com.yzj.springboot2.common;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 全局捕获异常
 */
@ControllerAdvice(basePackages = "com.yzj.springboot2.controller")
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RetJson erroResutlt(Exception ex) {

        HashMap<String, Object> data = new HashMap<String, Object>();

        String msg;
        msg = ex.getMessage();
        ex.printStackTrace();
        data.put("exmsg",msg);
        RetJson retJson = new RetJson();
        retJson.ret = 1;
        retJson.msg = "系统繁忙";
        retJson.data=data;
        return retJson;
    }

}
