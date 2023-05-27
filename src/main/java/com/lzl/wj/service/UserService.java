package com.lzl.wj.service;

import com.lzl.wj.dao.UserDao;
import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UuserDao uuserDao;

    public boolean isExist(String username){
        User user = getByName(username);
        return null != user;
    }
    public User getByName(String username) {
        return userDao.findByUsername(username);
    }
    public User get(String username,String password) {
        return userDao.getByUsernameAndPassword(username,password);
    }
    public void add(User user) {
        userDao.save(user);
    }
    public void updateName(Integer id, String name) {
        uuserDao.updateById(id, name);
    }
}
