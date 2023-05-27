package com.lzl.wj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lzl.wj.pojo.Talk;
import com.lzl.wj.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TalkServiceTest {

    @Autowired
    TalkService talkService;

    @Autowired
    UserTalksService userTalksService;

    @Autowired
    TalkMainService talkMainService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserFollowService userFollowService;


    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        int size = talkService.getAllTalks(list).size();
        System.out.println(size==2);

    }

//    @Test
//    public void test3() throws JsonProcessingException {
//        Talk talk = new Talk();
//        talk.setPublish_data(new Timestamp(new Date().getTime()));
//        talk.setContent("今天不是个好日子");
//        talk.setGood_number(0);;
//        talk.setLove_number(0);
//        userTalksService.addTalks(7, talk);
//    }

    @Test
    public void test4() throws JsonProcessingException {
        List<Talk> showTalks = talkMainService.getShowTalks(6);
        System.out.println(showTalks);
    }

    @Test
    public void test5() throws JsonProcessingException {
        List<Talk> talks = userTalksService.getTalks(6);
        for(Talk talk:talks) {
            System.out.println(talk.getContent());
        }
    }

}
