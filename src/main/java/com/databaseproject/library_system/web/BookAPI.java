package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Book;
import com.databaseproject.library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/template")
public class BookAPI {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books= bookService.findAll();
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
}
