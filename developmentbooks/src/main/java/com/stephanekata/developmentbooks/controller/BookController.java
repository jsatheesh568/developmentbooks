package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
  public ResponseEntity<List<Book>> getBooks(
          @RequestParam(required = false) String author,
          @RequestParam(required = false) String year) {

    if (author != null && year != null) {
      return ResponseEntity.ok(bookService.getBooksByAuthorAndYear(author, parseYear (year)));
    } else if (author != null) {
      return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    } else if (year != null) {
      return ResponseEntity.ok(bookService.getBooksByYear(parseYear(year)));
    } else {
      return ResponseEntity.ok(bookService.getAllBooks());
    }
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

  /**
   * Returns books published in a specific year.
   *
   * @param author publication author
   * @return filtered list of books
   */
  @GetMapping(value = "/books", params = "author")
  public List<Book> getBooksByAuthor(@RequestParam String author) {
    return bookService.getBooksByAuthor(author);
  }

  /**
   * Returns books published in a specific year & author.
   *
   * @param author , year publication author, year
   * @return filtered list of books
   */

  @GetMapping(value = "/books", params = {"author", "year"})
  public List<Book> getBooksByAuthorAndYear(@RequestParam String author, @RequestParam int year) {
    return bookService.getBooksByAuthorAndYear(author, year);
  }

  private int parseYear(String year) {
    return Integer.parseInt(year);
  }
}
