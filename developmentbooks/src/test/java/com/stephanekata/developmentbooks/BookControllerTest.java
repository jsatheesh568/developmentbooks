package com.stephanekata.developmentbooks;

import com.stephanekata.developmentbooks.controller.BookController;
import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookService;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;

  /** Test for fetching all books. */
  @Test
  void shouldFindAllBooks() throws Exception {
    List<Book> books =
        List.of(
            new Book("Clean Code", "Robert Martin", 2008),
            new Book("The Clean Coder", "Robert Martin", 2011),
            new Book("Clean Architecture", "Robert Martin", 2017),
            new Book("Test Driven Development by Example", "Kent Beck", 2003),
            new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004));

    Mockito.when(bookService.getAllBooks()).thenReturn(books);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)));
  }

  /** Test for fetching books by year using query param. */
  @Test
  void shouldFindBooksByYear() throws Exception {

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/bookstore/books?year=2011"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Clean Coder"));
  }
}
