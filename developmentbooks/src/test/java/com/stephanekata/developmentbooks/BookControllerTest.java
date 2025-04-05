package com.stephanekata.developmentbooks;

import com.stephanekata.developmentbooks.controller.BookController;
import com.stephanekata.developmentbooks.exception.GlobalExceptionHandler;
import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;

import java.util.Collections;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
@Import ( GlobalExceptionHandler.class)
public class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  /** Test for fetching all books. */
  @Test
  void shouldFindAllBooks() throws Exception {
    List<Book> books = bookList();

    Mockito.when(bookService.getAllBooks()).thenReturn(books);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books"))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(5)));
  }

  /** Test for fetching books by year using query param. */
  @Test
  void shouldFindBooksByYear() throws Exception {
    List<Book> allBooks = bookList();
    int year = allBooks.get(1).year();
    List<Book> bookByYear = filterBooksByYear(year);

    Mockito.when(bookService.getBooksByYear(year)).thenReturn(bookByYear);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books?year=" + year))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(bookByYear.size())))
        .andExpect( jsonPath("$[0].title").value("The Clean Coder"));
  }

  /** Test for fetching books by author using query param. */
  @Test
  void shouldFindBooksByAuthor() throws Exception {
    List<Book> allBooks = bookList();
    String author = allBooks.get(3).author();
    List<Book> booksByAuthor = filterBooksByAuthor(author);

    Mockito.when(bookService.getBooksByAuthor(author)).thenReturn(booksByAuthor);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books?author=" + author))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(1)))
        .andExpect(
            jsonPath("$[0].title")
                .value("Test Driven Development by Example"));
  }

  /** Test for fetching the books by same author using query param. */
  @Test
  void shouldReturnAllBooksForSameAuthor() throws Exception {
    String author = "Robert Martin";

    List<Book> bookBySameAuthor = filterBooksByAuthor(author);

    Mockito.when(bookService.getBooksByAuthor(author)).thenReturn(bookBySameAuthor);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books?author=" + author))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(3)))
        .andExpect( jsonPath("$[0].title").value("Clean Code"))
        .andExpect( jsonPath("$[1].title").value("The Clean Coder"))
        .andExpect( jsonPath("$[2].title").value("Clean Architecture"));
  }

  /** Test for fetching the books by author and year using query param. */
  @Test
  void shouldFindBooksByAuthorAndYear() throws Exception {
    Book sampleBook = bookList().get(1);
    String author = sampleBook.author();
    int year = sampleBook.year();

    List<Book> expectedBooks = filterBooksByAuthorAndYear(author, year);

    Mockito.when(bookService.getBooksByAuthorAndYear(author, year)).thenReturn(expectedBooks);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/bookstore/books")
                .param("author", author)
                .param("year", String.valueOf(year)))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(1)))
        .andExpect( jsonPath("$[0].title").value("The Clean Coder"));
  }

  /** Test for fetching when no books are available. */
  @Test
  void shouldReturnEmptyListWhenNoBooksAvailable() throws Exception {
    Mockito.when(bookService.getAllBooks()).thenReturn(Collections.emptyList());

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books"))
        .andExpect( status().isOk())
        .andExpect( jsonPath("$", Matchers.hasSize(0)));
  }

  /** Test for fetching when book year is invalid. */
  @Test
  void shouldReturnBadRequestForInvalidYearParameter() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books").param("year", "abc"))
        .andExpectAll(status().isBadRequest(), jsonPath("$.error").value("Invalid year format"));
  }

  /** Test for fetching when author param is empty. */
  @Test
  void shouldReturnBadRequestWhenAuthorParamIsEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books")
                    .param("author", ""))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Author parameter cannot be empty"));
  }
  /** Test for fetching when year param is empty. */

  @Test
  void shouldReturnBadRequestWhenYearParamIsEmpty() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books")
                    .param("year", ""))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").value("Year parameter cannot be empty"));
  }


  private List<Book> bookList() {
    return List.of(
        new Book("Clean Code", "Robert Martin", 2008),
        new Book("The Clean Coder", "Robert Martin", 2011),
        new Book("Clean Architecture", "Robert Martin", 2017),
        new Book("Test Driven Development by Example", "Kent Beck", 2003),
        new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004));
  }

  private List<Book> filterBooksByYear(int year) {
    return bookList().stream().filter(book -> book.year() == year).toList();
  }

  private List<Book> filterBooksByAuthor(String author) {
    return bookList().stream().filter(book -> book.author() == author).toList();
  }

  private List<Book> filterBooksByAuthorAndYear(String author, int year) {
    return bookList().stream()
        .filter(book -> book.author().equals(author) && book.year() == year)
        .toList();
  }
}
