package com.lzl.wj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzl.wj.dao.UserTalksDao;
import com.lzl.wj.pojo.Talk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTalksService {

    @Autowired
    UserTalksDao userTalksDao;

    private static ObjectMapper MAPPER = new ObjectMapper();

    public List<Talk> getTalks(Integer userId){
        return userTalksDao.getTalk(userId);

    }
    public List<Integer> getLoveId(Integer id) {
        return userTalksDao.getLoveId(id);
    }
    public void addLoveNumber(Integer talkId){
        userTalksDao.addLoveNumber(talkId);
    }
    public void addUserTalk(Integer userId, Integer talkId){
        userTalksDao.addUserTalk(userId, talkId);
    }
    public void subLoveNumber(Integer talkId){
        userTalksDao.subLoveNumber(talkId);
    }
    public void subUserTalk(Integer userId, Integer talkId){
        userTalksDao.subUserTalk(userId, talkId);
    }
}
