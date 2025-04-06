package com.stephanekata.developmentbooks;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorServiceTest {

  private PriceCalculatorService priceCalculatorService;

  private final Book cleanCode = new Book("Clean Code", "Robert Martin", 2008);
  private final Book refactoring = new Book("Refactoring", "Martin Fowler", 1999);
  private final Book tdd = new Book("TDD", "Kent Beck", 2002);

  @BeforeEach
  void setUp() {
    priceCalculatorService = new PriceCalculatorService();
  }

  @Test
  void shouldReturnBasePriceForSingleBook() {
    List<Book> books = List.of(cleanCode);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(50.0, result, 0.01);
  }

  @Test
  void shouldReturnTotalWithoutDiscountForSameBooks() {
    List<Book> books = List.of(cleanCode, cleanCode, cleanCode);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(150.0, result, 0.01);
  }

  @Test
  void shouldApplyFivePercentDiscountForTwoDifferentBooks() {
    List<Book> books = List.of(cleanCode, refactoring);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(95.0, result, 0.01);
  }

  @Test
  void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
    List<Book> books = List.of(cleanCode, refactoring, tdd);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(135.0, result, 0.01);
  }

  @Test
  void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
    Book book4 = new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002);
    List<Book> books = List.of(cleanCode, refactoring, tdd, book4);

    double result = priceCalculatorService.calculateTotalPrice(books);

    assertEquals(160.0, result, 0.01);
  }
}
