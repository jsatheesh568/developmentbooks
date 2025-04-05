package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController handles REST endpoints for book operations.
 *
 * Author: Satheeshkumar Subramanian
 */
@RestController
@RequestMapping("/api/v1/bookstore")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Returns books filtered by optional query parameters `author` and/or `year`.
   * - If both are null, returns all books.
   * - If author is present but empty, returns 400.
   * - If year is present but empty or non-numeric, returns 400.
   *
   * @param author optional filter by author
   * @param year   optional filter by publication year
   * @return filtered list of books
   */
  @GetMapping("/books")
  public ResponseEntity<List<Book>> getBooks(
          @RequestParam(required = false) String author,
          @RequestParam(required = false) String year) {

    if (author != null && author.isBlank()) {
      throw new IllegalArgumentException("Author parameter cannot be empty");
    }

    if (year != null && year.isBlank()) {
      throw new IllegalArgumentException("Year parameter cannot be empty");
    }

    if (year != null && !year.matches("\\d+")) {
      throw new IllegalArgumentException("Invalid year format");
    }

    if (author != null && year != null) {
      return ResponseEntity.ok(bookService.getBooksByAuthorAndYear(author, Integer.parseInt(year)));
    } else if (author != null) {
      return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    } else if (year != null) {
      return ResponseEntity.ok(bookService.getBooksByYear(Integer.parseInt(year)));
    } else {
      return ResponseEntity.ok(bookService.getAllBooks());
    }
  }
}
