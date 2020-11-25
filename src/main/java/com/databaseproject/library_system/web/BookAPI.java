package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Book;
import com.databaseproject.library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/template")
public class BookAPI {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books= bookService.findAll();
        Collections.sort(books, new Comparator<Book>(){
            @Override
            public int compare(Book a, Book b) {
                if (a.getStatus() == b.getStatus()) return (int)(a.getId() - b.getId());
                return a.getStatus() - b.getStatus();
            }
        });
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = null;
        try{
            book = bookService.findOne(id);
        } catch(Exception e) {

        }
        if(book == null) book = new Book();
        model.addAttribute(book);
        return "book";
    }

    @GetMapping("/books/input")
    public String inputPage(Model model) {
        //注意这里的book是给前端传递的model的名字，前端要获取这个model，就得使用跟这个model一样的名字
        model.addAttribute("book", new Book());
        return "input";
    }

    //这里有重定向
    @PostMapping("/books")
    public String post(Book book) {
        bookService.save(book);
        return "redirect:/template/books";
    }

    @GetMapping("books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "input";
    }
}
