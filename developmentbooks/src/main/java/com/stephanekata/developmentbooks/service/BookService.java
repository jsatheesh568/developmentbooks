package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * BookService provides business logic for book retrieval and filtering. Author: Satheeshkumar
 * Subramanian
 */
@Service
public class BookService {

  /**
   * Fetches all available books.
   *
   * @return list of books
   */
  public List<Book> getAllBooks() {
    return List.of(
        new Book("Clean Code", "Robert Martin", 2008),
        new Book("The Clean Coder", "Robert Martin", 2011),
        new Book("Clean Architecture", "Robert Martin", 2017),
        new Book("Test Driven Development by Example", "Kent Beck", 2003),
        new Book("Working Effectively With Legacy Code", "Michael C. Feathers", 2004));
  }

  /**
   * Filters books by year.
   *
   * @param year year to filter
   * @return list of books published in that year
   */
  public List<Book> getBooksByYear(int year) {
    return getAllBooks().stream().filter(book -> book.year() == year).toList();
  }

    /**
     * Filters books by year.
     *
     * @param author author to filter
     * @return list of books published in that author
     */
  public List<Book> getBooksByAuthor(String author) {
    return getAllBooks().stream().filter(book -> book.author().equals(author)).toList();
  }

    /**
     * Filters books by year & author.
     *
     * @param year,author year and author  to filter
     * @return list of books published in that year & author
     */
    private Predicate <Book> matchesAuthorAndYear( String author, int year) {
      return book -> book.author().equals(author) && book.year() == year;
    }

  public List<Book> getBooksByAuthorAndYear(String author, int year) {
    return getAllBooks().stream()
            .filter(matchesAuthorAndYear(author, year))
            .toList();
  }
}
