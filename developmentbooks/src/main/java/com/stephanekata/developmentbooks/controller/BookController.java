  package com.stephanekata.developmentbooks.controller;

  import static com.stephanekata.developmentbooks.utils.RequestValidator.validateBookQueryParams;

  import com.stephanekata.developmentbooks.model.Book;
  import com.stephanekata.developmentbooks.service.BookService;
  import io.swagger.v3.oas.annotations.Operation;
  import io.swagger.v3.oas.annotations.Parameter;
  import io.swagger.v3.oas.annotations.tags.Tag;
  import java.util.List;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  /**
   * BookController exposes REST endpoints for performing operations on books such as retrieving all
   * books, books by author, books by year, or books by both author and year.
   *
   * <p>Author: Satheeshkumar Subramanian Version: 1.0
   */
  @RestController
  @RequestMapping("/api/v1/bookstore")
  @Tag(name = "Book API", description = "Operations for retrieving development books")
  public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
      this.bookService = bookService;
    }

    /**
     * Retrieve books based on optional query parameters. Supports filtering by author, year, or both.
     * If no query parameters are provided, returns all books.
     *
     * @param author Optional author name.
     * @param year Optional publication year as a String.
     * @return List of books matching the criteria.
     */
    @GetMapping("/books")
    @Operation(
        summary = "Get books by optional filters",
        description = "Retrieve books by author, year, both, or get all books if no filters provided")
    public ResponseEntity<List<Book>> getBooks(
        @Parameter(description = "Author of the book") @RequestParam(required = false) String author,
        @Parameter(description = "Year the book was published") @RequestParam(required = false)
            String year) {

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
