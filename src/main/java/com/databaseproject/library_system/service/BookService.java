package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Book;
import com.databaseproject.library_system.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    //can be used for both add and update
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findOne(long id) {
        return bookRepository.findById(id).get();
    }

    public void deleteOne(long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    public List<Book> findByDescriptionEndsWith(String des) {
        return bookRepository.findByDescriptionEndsWith(des);
    }

    public List<Book> findByJPQL(long length) {
        return bookRepository.findByJPQL(length);
    }

    @Transactional
    public int updateByJPQL(String author, long id) {
        return bookRepository.updateByJPQL(author, id);
    }

    @Transactional
    public int deleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }
}
