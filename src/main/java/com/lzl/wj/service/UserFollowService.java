package com.lzl.wj.service;

import com.lzl.wj.dao.UserFollowsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowService {

    @Autowired
    UserFollowsDao userFollowsDao;

    public void addFollow(Integer id, Integer followId) {
        userFollowsDao.addFollow(id, followId);
    }

    public List<Integer> getAllFollow(Integer id) {
        return userFollowsDao.getAllFollows(id);
    }

    public void subFollow(Integer id, Integer followId) {
        userFollowsDao.subFollow(id, followId);
    }
}
