package com.lzl.wj.service;

import com.lzl.wj.dao.CommentDao;
import com.lzl.wj.dao.ImageDao;
import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.pojo.Comment;
import com.lzl.wj.pojo.Reply;
import com.lzl.wj.vo.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    ReplyService replyService;

    @Autowired
    ImageService imageService;

    @Autowired
    UuserDao uuserDao;

    public List<CommentsVo> getComment(Integer talk_id){
        List<Comment> comments = commentDao.getById(talk_id);
        List<CommentsVo> commentsVos = new ArrayList<>();
        for(Comment comment : comments) {
            CommentsVo commentsVo = new CommentsVo();
            commentsVo.setComment(comment);
            List<Reply> reply = replyService.getReply(comment.getId());
            Collections.sort(reply);
            commentsVo.setReplies(reply);
            Integer from_id = comment.getFrom_id();
            commentsVo.setImageLocation(imageService.getImage(from_id));
            commentsVo.setUserName(uuserDao.getById(from_id));
            commentsVos.add(commentsVo);
        }
        Collections.sort(commentsVos);
        return commentsVos;
    }

    public void subComment(Comment comment) {
        commentDao.subComment(comment);
    }

}
