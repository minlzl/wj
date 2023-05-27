package com.lzl.wj.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ChangeDao {
    void addTalkCommentNumber(Integer id);
}
