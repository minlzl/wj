package com.lzl.wj.controller;

import com.lzl.wj.pojo.Book;
import com.lzl.wj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping("/deleteBooks")
    public void deleteBook(@RequestBody Book book) {
        if (book == null) {
            System.out.println("删除失败");
            return ;
        }
        bookService.deleteBookById(book.getId());
    }

    @GetMapping("categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid")Integer cid) {
        if(cid == null) {
            return new ArrayList<Book>();
        }
        return bookService.findByCategory(cid);
    }

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book) {
        if(book == null) {
            return;
        }
        bookService.addBook(book);
    }
}
