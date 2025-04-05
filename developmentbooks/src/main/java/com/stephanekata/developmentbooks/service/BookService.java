package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BookService provides business logic for book retrieval and filtering.
 * Author: Satheeshkumar Subramanian
 */

@Service
public class BookService {

    /**
     * Fetches all available books.
     * @return list of books
     */
    public List< Book > getAllBooks() {
        return List.of(
                new Book("Clean Code", "Robert C. Martin", 2008),
                new Book("Effective Java", "Joshua Bloch", 2018),
                new Book("Refactoring", "Martin Fowler", 1999),
                new Book("The Pragmatic Programmer", "Andrew Hunt", 1999),
                new Book("Test Driven Development", "Kent Beck", 2002)
        );
    }

    /**
     * Filters books by year.
     * @param year year to filter
     * @return list of books published in that year
     */
    public List<Book> getBooksByYear ( int year ) {
        return getAllBooks().stream()
                .filter(book -> book.year() == year)
                .toList ();
    }
}
