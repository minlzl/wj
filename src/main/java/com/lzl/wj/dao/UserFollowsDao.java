package com.lzl.wj.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowsDao {
    List<Integer> getAllFollows(Integer userId);
    int addFollow(Integer id, Integer followId);
    int subFollow(Integer id, Integer followId);
}
