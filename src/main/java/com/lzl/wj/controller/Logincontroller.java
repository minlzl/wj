package com.lzl.wj.controller;


import com.lzl.wj.pojo.User;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.utils.RedisUtils;
import com.lzl.wj.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class Logincontroller {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CookieUtils cookieUtils;

    @CrossOrigin
    @PostMapping("/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestuser, HttpSession session, HttpServletResponse response){
        String username = requestuser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestuser.getPassword());
        try {
            System.out.println(subject.isAuthenticated());
            subject.login(usernamePasswordToken);
            System.out.println(subject.isAuthenticated());
            session.setAttribute("user", username);
            Cookie cookie = new Cookie("uid", StringUtils.getRandomString(16));
            response.addCookie(cookie);
            return ResultFactory.buildSuccessResult(requestuser);
        } catch (AuthenticationException e) {
            return ResultFactory.buildFailResult(requestuser);
        }
    }

    @GetMapping("/api/logout")
    @ResponseBody
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    @ResponseBody
    @GetMapping("/api/authentication")
    public String authentication(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            if(cookie.getName().equals("uid")) {
                redisUtils.setUserId(cookie.getValue(), (String) session.getAttribute("user"));
                cookie.setMaxAge(60*60*24*3);
                response.addCookie(cookie);
            }
        }
        return "身份认证成功"+cookieUtils.getUserName(cookies);
    }
}
