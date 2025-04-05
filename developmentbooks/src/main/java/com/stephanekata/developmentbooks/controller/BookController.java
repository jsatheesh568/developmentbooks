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
        return List.of ();
    }
}
