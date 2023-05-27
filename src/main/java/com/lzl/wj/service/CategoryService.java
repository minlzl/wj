package com.lzl.wj.service;

import com.lzl.wj.dao.CategoryDao;
import com.lzl.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }
}
