package com.lzl.wj.dao;

import com.lzl.wj.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao {

    User isExist(String username) ;
}
