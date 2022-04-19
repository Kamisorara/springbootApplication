package com.application.controller;

import com.application.entity.Book;
import com.application.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/findAll")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
