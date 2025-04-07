package com.stephanekata.developmentbooks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceCalculatorServiceTest {

  private PriceCalculatorService priceCalculatorService;

  private final Book cleanCode = new Book("Clean Code", "Robert Martin", 2008);

  private final Book cleanCoder = new Book("The Clean Coder", "Robert Martin", 2011);
  private final Book CleanArchitecture = new Book("Clean Architecture", "Robert Martin", 2017);
  private final Book tdd = new Book("TDD", "Kent Beck", 2003);

  private final Book legacyCode =
      new Book("Work Effectively with legacy code", "Michael C.Feathers", 2004);

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
    List<Book> books = List.of(cleanCode, CleanArchitecture);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(95.0, result, 0.01);
  }

  @Test
  void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
    List<Book> books = List.of(cleanCode, CleanArchitecture, tdd);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(135.0, result, 0.01);
  }

  @Test
  void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
    List<Book> books = List.of(cleanCode, CleanArchitecture, tdd, cleanCoder);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(160.0, result, 0.01);
  }

  @Test
  void shouldApplyTwentyFivePercentDiscountForFiveDifferentBooks() {
    List<Book> books = List.of(cleanCode, CleanArchitecture, tdd, cleanCoder, legacyCode);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(187.5, result, 0.01);
  }

  @Test
  void shouldHandleNullBookInList() {
    List<Book> books = new ArrayList<>();
    books.add(cleanCode);
    books.add(null);
    books.add(cleanCoder);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(95.0, result, 0.01);
  }

  @Test
  void shouldGroupBooksForMaximumDiscount() {
    List<Book> books =
        List.of(
            cleanCode,
            cleanCode,
            cleanCoder,
            cleanCoder,
            CleanArchitecture,
            CleanArchitecture,
            tdd,
            legacyCode);
    double result = priceCalculatorService.calculateTotalPrice(books);
    assertEquals(320.0, result, 0.01);
  }
}
