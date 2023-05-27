package com.lzl.wj.controller;

import com.lzl.wj.dao.ImageDao;
import com.lzl.wj.pojo.Comment;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.CommentService;
import com.lzl.wj.service.ImageService;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CastUtils;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.vo.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @RequestMapping("/comments")
    public Result getComment(@RequestParam Integer id) {
        if(id == null) {
            return ResultFactory.buildFailResult(null);
        }
        List<CommentsVo> commentsVos = commentService.getComment(id);
        return ResultFactory.buildSuccessResult(commentsVos);
    }

    @RequestMapping("/subComment")
    public Result subComment(@RequestBody Comment comment, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fall");
        }
        int id = userService.getByName(userName).getId();
        comment.setFrom_id(id);
        comment.setPubtime(new Timestamp(new Date().getTime()));
        commentService.subComment(comment);
        String image = imageService.getImage(id);
        return ResultFactory.buildSuccessResult(new CommentsVo(comment, new ArrayList<>(), userName, image));
    }
}
