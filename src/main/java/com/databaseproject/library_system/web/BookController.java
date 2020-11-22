package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Book;
import com.databaseproject.library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @PostMapping("/books")
    public Book post(Book book) {
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    @PutMapping("/books")
    public Book update(Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id) {
        bookService.deleteOne(id);
    }
    @PostMapping("/books/by")
    public List<Book> findByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }
    @PostMapping("/books/author/and/status")
    public List<Book> findByAuthorAndStatus(@RequestParam String author, @RequestParam int status) {
        return bookService.findByAuthorAndStatus(author, status);
    }
    @PostMapping("/books/by/end/with")
    public List<Book> findByDescriptionEndsWith(@RequestParam String des) {
        return bookService.findByDescriptionEndsWith(des);
    }
    @PostMapping("/books/find/by/JPQL")
    public List<Book> findByJPQL(@RequestParam long length) {
        return bookService.findByJPQL(length);
    }

    @PostMapping("/books/update/by/JPQL")
    public int updateByJPQL(@RequestParam String author, @RequestParam long id) {
        return bookService.updateByJPQL(author, id);
    }

    @PostMapping("/books/delete/by/JPQL")
    public int deleteByJPQL(@RequestParam long id) {
        return bookService.deleteByJPQL(id);
    }
}
