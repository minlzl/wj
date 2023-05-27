package com.lzl.wj.dao;

import com.lzl.wj.pojo.Talk;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTalksDao {

    List<Talk> getTalk(Integer userId);
    List<Integer> getLoveId(Integer userId);
    void addLoveNumber(Integer talkId);
    void addUserTalk(Integer userId, Integer talkId);
    void subLoveNumber(Integer talkId);
    void subUserTalk(Integer userId, Integer talkId);
}
