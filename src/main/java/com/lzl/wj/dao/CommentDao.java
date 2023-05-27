package com.lzl.wj.dao;

import com.lzl.wj.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> getById(Integer id);
    void subComment(Comment comment);
}
