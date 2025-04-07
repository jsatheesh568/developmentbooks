package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

/** Applies a 10% discount when exactly 3 distinct book titles are purchased. */
@Component
public class ThreeBookDiscountStrategy implements DiscountStrategy {

  private static final int REQUIRED_UNIQUE_BOOKS = 3;
  private static final double DISCOUNT_RATE = 0.10;

  /**
   * Checks whether this discount strategy is applicable.
   *
   * @param books the list of books to evaluate
   * @return true if exactly 3 distinct book titles are present, false otherwise
   */
  @Override
  public boolean isApplicable(List<Book> books) {
    return books.stream().map(Book::title).distinct().count() == REQUIRED_UNIQUE_BOOKS;
  }

  /**
   * Applies the 10% discount to the base price.
   *
   * @param books the list of books (not directly used in discount logic)
   * @param basePrice the original price before discount
   * @return the amount of discount to subtract from the base price
   */
  @Override
  public double applyDiscount(List<Book> books, double basePrice) {
    return basePrice * DISCOUNT_RATE;
  }
}
