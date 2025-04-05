package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/v1/bookstore")
public class BookController {

    @GetMapping("/books")
    public List < Book > getAllBooks(){
        return List.of(
                new Book("Clean Code", "Robert Martin", 2008),
                new Book("The Clean Coder", "Robert Martin", 2011),
                new Book("Clean Architecture", "Robert Martin", 2017),
                new Book("Test Driven Development by Example", "Kent Beck", 2003),
                new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004)
        );
    }
}
