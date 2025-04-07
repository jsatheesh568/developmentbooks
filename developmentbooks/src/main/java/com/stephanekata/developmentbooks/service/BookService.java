package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * {@code BookService} provides business logic for retrieving and filtering books.
 *
 * <p>This service offers functionality to: - Get all available books - Filter books by year -
 * Filter books by author - Filter books by both author and year
 *
 * <p><strong>Author:</strong> Satheeshkumar Subramanian
 */
@Service
public class BookService {

  /**
   * Returns a list of all books available in the system.
   *
   * @return a list of hardcoded {@link Book} objects
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
   * Retrieves all books published in the specified year.
   *
   * @param year the year to filter books by
   * @return a list of books published in the given year
   */
  public List<Book> getBooksByYear(int year) {
    return filterBooks(book -> book.year() == year);
  }

  /**
   * Retrieves all books written by the specified author.
   *
   * @param author the name of the author
   * @return a list of books written by the specified author
   */
  public List<Book> getBooksByAuthor(String author) {
    return filterBooks(book -> book.author().equals(author));
  }

  /**
   * Retrieves books written by the specified author and published in the specified year.
   *
   * @param author the name of the author
   * @param year the publication year
   * @return a list of books matching both the author and the year
   */
  public List<Book> getBooksByAuthorAndYear(String author, int year) {
    return filterBooks(matchesAuthorAndYear(author, year));
  }

  /**
   * Creates a reusable predicate to filter books by a specific author and year.
   *
   * @param author the author's name
   * @param year the year of publication
   * @return a predicate to match both author and year
   */
  private Predicate<Book> matchesAuthorAndYear(String author, int year) {
    return book -> book.author().equals(author) && book.year() == year;
  }

  /**
   * Filters books from the full list using the given predicate condition.
   *
   * @param predicate the filtering logic
   * @return the filtered list of books
   */
  private List<Book> filterBooks(Predicate<Book> predicate) {
    return getAllBooks().stream().filter(predicate).toList();
  }
}
