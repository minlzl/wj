package com.lzl.wj.controller;

import com.lzl.wj.pojo.JotterArticle;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/save/article")
    public Result saveArticle(@RequestBody JotterArticle article) {
       articleService.addOrUpdate(article);
       return ResultFactory.buildSuccessResult(article);
    }

    @RequestMapping("/article/{size}/{page}")
    public Page list(@PathVariable("size") Integer size, @PathVariable("page") Integer page) {
        return articleService.list(page-1,size);
    }

    @RequestMapping("/article/{id}")
    public JotterArticle getArticle(@PathVariable("id") Integer id) {
        return articleService.findById(id);
    }
}
