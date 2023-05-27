package com.lzl.wj.controller;

import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.UserFollowService;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FollowController {

    @Autowired
    UserFollowService userFollowService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @RequestMapping("/addFollow/{id}")
    public Result addFollow(@PathVariable Integer id, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int userId = userService.getByName(userName).getId();
        userFollowService.addFollow(userId, id);
        return ResultFactory.buildSuccessResult("yes");
    }

    @RequestMapping("/subFollow/{id}")
    public Result subFollow(@PathVariable Integer id, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int userId = userService.getByName(userName).getId();
        try {
            userFollowService.subFollow(userId, id);
            return ResultFactory.buildSuccessResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("fail");
        }
    }

    @RequestMapping("/getFollow")
    public Result getFollow(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int userId = userService.getByName(userName).getId();
        List<Integer> allFollow = userFollowService.getAllFollow(userId);
        return ResultFactory.buildSuccessResult(allFollow);
    }
}
