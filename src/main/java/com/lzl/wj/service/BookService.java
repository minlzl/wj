package com.lzl.wj.service;

import com.lzl.wj.dao.BookDao;
import com.lzl.wj.pojo.Book;
import com.lzl.wj.pojo.Category;
import com.lzl.wj.utils.CastUtils;
import com.lzl.wj.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RedisUtils redisUtils;

    public List<Book> findAll() {
        List<Book> books;
        String key = "bookList";
        Object all = redisUtils.getAll(key);
        if(all == null) {
            books = bookDao.findAll();
            redisUtils.set(key, books);
        } else {
            books = CastUtils.objectConvertToList(all, Book.class);
        }
        return books;
    }

    public List<Book> findByCategory(Integer id) {
        if(id == 0)
            return findAll();
        String key = "book_category_"+id;
        Object all = redisUtils.getAll(key);
        List<Book> books;
        if(all == null) {
            Category category = categoryService.getById(id);
            books = bookDao.findByCategory(category);
            redisUtils.set(key, books);
        } else {
            books = CastUtils.objectConvertToList(all, Book.class);
        }
        return books;
    }

    public List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2) {
        return bookDao.findAll();
    }

    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

}
