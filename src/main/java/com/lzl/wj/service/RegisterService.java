package com.lzl.wj.service;

import com.lzl.wj.dao.RegisterDao;
import com.lzl.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    RegisterDao registerDao;

    public boolean isExist(String username) {
//        User exist = registerDao.isExist(username);
        return registerDao.isExist(username) != null;
    }
}
