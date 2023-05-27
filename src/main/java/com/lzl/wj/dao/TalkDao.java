package com.lzl.wj.dao;

import com.lzl.wj.pojo.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkDao extends JpaRepository<Talk, Integer> {
}
