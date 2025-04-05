package com.stephanekata.developmentbooks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceCalculatorServiceTest {

  @Test
  public void testSingleBookReturnsBasePrice() {
    PriceCalculatorService priceCalculatorService = new PriceCalculatorService();
    List<Book> books = List.of(new Book("Clean Code", "Robert Martin", 2008));

    double result = priceCalculatorService.calculateTotalPrice(books);

    assertEquals(50.0, result);
  }

  @Test
  public void testMultipleBooksReturnTotalWithoutDiscount() {
    PriceCalculatorService priceCalculatorService = new PriceCalculatorService();

    List<Book> books = List.of(
            new Book("Clean Code", "Robert Martin", 2008),
            new Book("Refactoring", "Martin Fowler", 1999),
            new Book("TDD", "Kent Beck", 2002)
    );

    double result = priceCalculatorService.calculateTotalPrice(books);
    
    assertEquals(150.0, result);
  }

  @Test
  public void testTwoDifferentBooksWithFivePercentDiscount() {
    PriceCalculatorService priceCalculatorService = new PriceCalculatorService();

    List<Book> books = List.of(
            new Book("Clean Code", "Robert Martin", 2008),
            new Book("Refactoring", "Martin Fowler", 1999)
    );

    double result = priceCalculatorService.calculateTotalPrice(books);

    assertEquals(95.0, result);
  }

  @Test
  public void testThreeDifferentBooksWithTenPercentDiscount() {
    PriceCalculatorService priceCalculatorService = new PriceCalculatorService();

    List<Book> books = List.of(
            new Book("Clean Code", "Robert Martin", 2008),
            new Book("Refactoring", "Martin Fowler", 1999),
            new Book("TDD", "Kent Beck", 2002)
    );

    double result = priceCalculatorService.calculateTotalPrice(books);
    
    assertEquals(135.0, result);
  }


}
