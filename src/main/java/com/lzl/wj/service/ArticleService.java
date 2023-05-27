package com.lzl.wj.service;

import com.lzl.wj.dao.JotterArticleDao;
import com.lzl.wj.pojo.JotterArticle;
import com.lzl.wj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ArticleService {

    @Autowired
    JotterArticleDao jotterArticleDao;

    @Autowired
    RedisUtils redisUtils;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return  jotterArticleDao.findAll(PageRequest.of(page, size, sort));
    }

    public JotterArticle findById(int id) {
        String key = "article_"+id;
        Object all = redisUtils.getAll(key);
        JotterArticle article;
        if(all == null) {
            article = jotterArticleDao.findById(id);
            redisUtils.set(key, article);
        } else {
            article = (JotterArticle) all;
        }
        return article;
    }

    public void addOrUpdate(JotterArticle article) {
        article.setArticleDate(new Date(new java.util.Date().getTime()));
        String key = "article_"+article.getId();
        redisUtils.remove(key);
        jotterArticleDao.save(article);
    }

    public void delete(int id) {
        String key = "article_"+id;
        redisUtils.remove(key);
        jotterArticleDao.deleteById(id);
    }
}
