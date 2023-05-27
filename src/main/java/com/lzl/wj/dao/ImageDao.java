package com.lzl.wj.dao;

import com.lzl.wj.pojo.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao {
    String getImage(Integer id);
    void addImage(Image image);
}
