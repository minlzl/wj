package com.lzl.wj.vo;

import com.lzl.wj.pojo.Comment;
import com.lzl.wj.pojo.Reply;

import java.util.List;

public class CommentsVo implements Comparable<CommentsVo>{
    private Comment comment;
    private List<Reply> replies;
    private String userName;
    private String imageLocation;

    public CommentsVo() {
    }

    public CommentsVo(Comment comment, List<Reply> replies, String userName, String imageLocation) {
        this.comment = comment;
        this.replies = replies;
        this.userName = userName;
        this.imageLocation = imageLocation;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    @Override
    public int compareTo(CommentsVo o) {
        return comment.compareTo(o.comment);
    }
}
