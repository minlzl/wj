package com.lzl.wj.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UuserDao {
    String getById(Integer id);
    void updateById(Integer id, String name);
}
