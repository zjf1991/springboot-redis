package com.yzj.springbootredis.utils;


import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {


private  final  static  String COOKIE_DOMAIN=".yzj.com";
private  final  static  String COOKIE_NAME="yzj_login_token";


    /**
     * 读入Cookie
     * @param request
     * @return
     */
    public static  String readLoginToken(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(StringUtils.pathEquals(cookie.getName(),COOKIE_NAME)){
                        return cookie.getValue();
                }
            }
        }
        return null;
}

    /**
     * 写入Cookie
     * @param response
     * @param token
     */
    public static  void writeLoginToken(HttpServletResponse response, String token){
    Cookie ck=new Cookie(COOKIE_NAME,token);
    ck.setDomain(COOKIE_DOMAIN);
    ck.setPath("/");
    ck.setHttpOnly(true);
    ck.setMaxAge(60*60*24*365);
    response.addCookie(ck);
}


/**
 * 删除cookie
 */
public static  void deleteLoginToken(HttpServletRequest request, HttpServletResponse response){
    Cookie[] cookies=request.getCookies();
    if(cookies!=null){
        for(Cookie cookie:cookies){
            if(StringUtils.pathEquals(cookie.getName(),COOKIE_NAME)){
                cookie.setDomain(COOKIE_DOMAIN);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return;
            }
        }
    }
}
}
