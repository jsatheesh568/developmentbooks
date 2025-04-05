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
   * Returns a list of all books available in the system.
   *
   * @return a list of hardcoded Book objects.
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
   * Filters and returns books published in the specified year.
   *
   * @param year the year to filter books by
   * @return a list of books published in the given year
   */
  public List<Book> getBooksByYear(int year) {
    return filterBooks(book -> book.year() == year);
  }

  /**
   * Filters and returns books written by the specified author.
   *
   * @param author the name of the author
   * @return a list of books written by the given author
   */
  public List<Book> getBooksByAuthor(String author) {
    return filterBooks(book -> book.author().equals(author));
  }

  /**
   * Filters and returns books written by the specified author and published in the specified year.
   *
   * @param author the name of the author
   * @param year the year of publication
   * @return a list of books that match both author and year
   */
  public List<Book> getBooksByAuthorAndYear(String author, int year) {
    return filterBooks(matchesAuthorAndYear(author, year));
  }

  /**
   * Creates a reusable predicate that checks if a book matches both a given author and publication year.
   *
   * @param author the author's name
   * @param year the year of publication
   * @return a Predicate<Book> for filtering
   */
  private Predicate<Book> matchesAuthorAndYear(String author, int year) {
    return book -> book.author().equals(author) && book.year() == year;
  }

  /**
   * Filters the list of all books using a given predicate condition.
   *
   * @param predicate the filtering logic as a Predicate<Book>
   * @return a filtered list of books that satisfy the predicate
   */
  private List<Book> filterBooks(Predicate<Book> predicate) {
    return getAllBooks().stream().filter(predicate).toList();
  }
}
