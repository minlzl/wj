package com.lzl.wj.controller;

import com.lzl.wj.pojo.Reply;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.ReplyService;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @RequestMapping("/subReply")
    public Result subReply(@RequestBody Map<String, String> params, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int uid = userService.getByName(userName).getId();
        String content = params.get("content");
        String strId = params.get("id");
        int id = Integer.parseInt(strId);
        String strCommentId = params.get("commentId");
        int commentId = Integer.parseInt(strCommentId);
        Reply reply = new Reply();
        reply.setComment_id(commentId);
        reply.setContent(content);
        reply.setFrom_id(uid);
        reply.setPubtime(new Timestamp(new Date().getTime()));
        reply.setTo_id(id);
        replyService.addReply(reply);
        return ResultFactory.buildSuccessResult(reply);
    }
}
