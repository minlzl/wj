package com.lzl.wj.dao;

import com.lzl.wj.pojo.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDao {
    List<Reply> getReply(Integer commentId);
    void addReply(Reply reply);
}
