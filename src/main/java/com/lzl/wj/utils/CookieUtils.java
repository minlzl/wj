package com.lzl.wj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieUtils {

    @Autowired
    RedisUtils redisUtils;

    public String getUserName(Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if("uid".equals(cookie.getName())) {
                String all = redisUtils.getAllUser(cookie.getValue());
                return String.valueOf(all);
            }
        }
        return null;
    }

    public String getSessionId(Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            if("uid".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
