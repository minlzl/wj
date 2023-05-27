package com.lzl.wj.controller;

import com.lzl.wj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RedisController {

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/readerNumber")
    public String readerNumber() {
        return redisUtils.getAndIncrement("readernumber");
    }
}
