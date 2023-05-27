package com.lzl.wj.dao;

import com.lzl.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    User findByUsername(String username);
    User getByUsernameAndPassword(String username,String password);

}
