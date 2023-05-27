package com.lzl.wj.dao;

import com.lzl.wj.pojo.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JotterArticleDao extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
