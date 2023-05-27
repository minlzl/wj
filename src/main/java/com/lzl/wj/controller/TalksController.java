package com.lzl.wj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lzl.wj.pojo.Talk;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.*;
import com.lzl.wj.utils.CastUtils;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.vo.ContentVo;
import com.lzl.wj.vo.TalkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TalksController {

    @Autowired
    TalkMainService talkMainService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @Autowired
    TalkService talkService;

    @Autowired
    TalkImgService talkImgService;

    @Autowired
    UserTalksService userTalksService;

    @RequestMapping("/getTalks")
    public Result getAllTalks(HttpServletRequest request) throws JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int id = userService.getByName(userName).getId();
        List<Talk> showTalks = talkMainService.getShowTalks(id);
        return ResultFactory.buildSuccessResult(showTalks);
    }

    @RequestMapping("/getTalkVos")
    public Result getTalkVos(@RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int id = userService.getByName(userName).getId();
        List<TalkVo> showTalkVos = talkMainService.getShowTalkVos(id, currentPage, pageSize);
        return ResultFactory.buildSuccessResult(showTalkVos);
    }

    @RequestMapping("/gatTalk")
    public Result getTalk(@RequestParam Integer id) {
        if(id != null)
            return ResultFactory.buildSuccessResult(talkService.findById(id));
        return ResultFactory.buildFailResult(null);
    }

    @RequestMapping("/getTalkVo")
    public Result getTalkVo(@RequestParam Integer id) {
        if (id != null) {
            return ResultFactory.buildSuccessResult(talkService.findVoById(id));
        } else {
            return ResultFactory.buildFailResult(null);
        }
    }

    @PostMapping("/subTalks")
    public Result subTalks(@RequestBody ContentVo contentVo, HttpServletRequest request) throws JsonProcessingException {
        Talk talk = new Talk();
        talk.setContent(contentVo.getContent());
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int id = userService.getByName(userName).getId();
        talk.setPublish_data(new Timestamp(new Date().getTime()));
        talk.setUser_id(id);
        talk.setLove_number(0);
        talk.setComment_number(0);
        talk = talkService.saveTalk(talk);
        System.out.println(talk);
        List<String> imgs = contentVo.getImgs();
        if (imgs != null && imgs.size() > 0) {
            for (String img : imgs) {
                talkImgService.addImgs(talk.getTalk_id(), img);
            }
        }
        return ResultFactory.buildSuccessResult(talk);
    }

    @RequestMapping("/getAllTalks")
    public Result getAllTalks() {
        List<Talk> talks = talkService.getTalks();
        return ResultFactory.buildSuccessResult(talks);
    }

    @RequestMapping("/getLove")
    public Result getLove(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int id = userService.getByName(userName).getId();
        List<Integer> loveId = userTalksService.getLoveId(id);
        return ResultFactory.buildSuccessResult(loveId);
    }

    @RequestMapping("/addLove")
    public Result addLove(HttpServletRequest request,@RequestParam Integer id) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int userId = userService.getByName(userName).getId();
        userTalksService.addLoveNumber(id);
        userTalksService.addUserTalk(userId, id);
        return ResultFactory.buildSuccessResult(null);
    }

    @RequestMapping("/subLove")
    public Result subLove(HttpServletRequest request, @RequestParam Integer id) {
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
        int userId = userService.getByName(userName).getId();
        userTalksService.subLoveNumber(id);
        userTalksService.subUserTalk(userId, id);
        return ResultFactory.buildSuccessResult(null);
    }

    @RequestMapping("/addCommentNumber/{id}")
    public Result addCommentNumber(@PathVariable Integer id) {
        if (id == null)
            return ResultFactory.buildFailResult("null");
        talkService.addTalkCommentNumber(id);
        return ResultFactory.buildSuccessResult(id);
    }

    @RequestMapping("/deleteById")
    public Result delTalk(@RequestParam("id") Integer id) {
        talkService.deleteById(id);
        return ResultFactory.buildSuccessResult(null);
    }
}
