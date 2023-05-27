package com.lzl.wj.dao;

import com.lzl.wj.pojo.Book;
import com.lzl.wj.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    List<Book> findAll();
    List<Book> findByCategory(Category category);
    int deleteBookById(Integer id);
    int addBook(Book book);
//    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
