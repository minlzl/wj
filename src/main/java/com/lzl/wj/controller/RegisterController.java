package com.lzl.wj.controller;

import com.lzl.wj.pojo.User;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.RegisterService;
import com.lzl.wj.service.UserFollowService;
import com.lzl.wj.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    UserFollowService userFollowService;

    @Autowired
    RegisterService service;

    @RequestMapping("/register")
    public Result register(@RequestBody User user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        boolean exist = service.isExist(userName);
        if (exist) {
            return ResultFactory.buildFailResult(user);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        user.setSalt(salt);
        userService.add(user);
        User user1 = userService.getByName(user.getUsername());
        userFollowService.addFollow(user1.getId(), user1.getId());
        return ResultFactory.buildSuccessResult(user);
    }
}
