package com.lzl.wj.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkImgDao {
    List<String> getImgs(@Param("id") Integer talk_id);
    void addImgs(@Param("id") Integer talk_id, @Param("img") String img);
}
