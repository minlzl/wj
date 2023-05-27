package com.lzl.wj.controller;

import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/changeName")
    public Result changeName(@RequestBody Map<String, String> params, HttpServletRequest request, HttpSession session) {
        String name = params.get("name");
        if (name == null)
            return ResultFactory.buildFailResult(null);
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int id = userService.getByName(userName).getId();
        if (userService.isExist(name)) {
            return ResultFactory.buildFailResult("fail");
        }
        userService.updateName(id, name);
        String sessionId = cookieUtils.getSessionId(cookies);
        System.out.println(sessionId);
        redisUtils.setUserId(sessionId, name);
        session.setAttribute("user", name);
        return ResultFactory.buildSuccessResult(name);
    }
}
