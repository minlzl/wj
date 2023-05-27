package com.lzl.wj.service;

import com.lzl.wj.dao.ReplyDao;
import com.lzl.wj.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyDao replyDao;

    public List<Reply> getReply(Integer comment_id) {
        return replyDao.getReply(comment_id);
    }

    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }
}
