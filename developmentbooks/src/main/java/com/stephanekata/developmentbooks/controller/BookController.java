package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stephanekata.developmentbooks.utils.RequestValidator.validateBookQueryParams;

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

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getBooks(
          @RequestParam(required = false) String author,
          @RequestParam(required = false) String year) {

    validateBookQueryParams(author, year);

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
