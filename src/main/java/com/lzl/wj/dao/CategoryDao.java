package com.lzl.wj.dao;

import com.lzl.wj.pojo.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao {
    Category getById(Integer id);
}
