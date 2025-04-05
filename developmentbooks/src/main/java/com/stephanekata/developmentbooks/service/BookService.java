package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public List< Book > getAllBooks() {
        return List.of(
                new Book("Clean Code", "Robert C. Martin", 2008),
                new Book("Effective Java", "Joshua Bloch", 2018),
                new Book("Refactoring", "Martin Fowler", 1999),
                new Book("The Pragmatic Programmer", "Andrew Hunt", 1999),
                new Book("Test Driven Development", "Kent Beck", 2002)
        );
    }
}
