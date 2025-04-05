package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** BookController handles REST endpoints for book operations. Author: Satheeshkumar Subramanian */
@RestController
@RequestMapping("/api/v1/bookstore")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Returns all available books.
   *
   * @return List of books
   */
  @GetMapping("/books")
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  /**
   * Returns books published in a specific year.
   *
   * @param year publication year
   * @return filtered list of books
   */
  @GetMapping(value = "/books", params = "year")
  public List<Book> getBooksByYear(@RequestParam int year) {
    return bookService.getBooksByYear(year);
  }
}
