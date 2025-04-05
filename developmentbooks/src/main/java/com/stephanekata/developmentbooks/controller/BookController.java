package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/v1/bookstore")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public List < Book > getAllBooks(){
        return bookService.getAllBooks();
    }
}
