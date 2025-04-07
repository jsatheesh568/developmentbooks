package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import java.util.List;

/** Applies a 20% discount when exactly 4 distinct book titles are purchased. */
public class FourBookDiscountStrategy implements DiscountStrategy {

  private static final int REQUIRED_BOOK_COUNT = 4;
  private static final double DISCOUNT_RATE = 0.20;

  /**
   * Determines if this strategy can be applied based on distinct book titles.
   *
   * @param books the list of books being evaluated
   * @return true if exactly 4 distinct titles are present, false otherwise
   */
  @Override
  public boolean isApplicable(List<Book> books) {
    return books.stream().map(Book::title).distinct().count() == REQUIRED_BOOK_COUNT;
  }

  /**
   * Calculates the discount amount to be applied.
   *
   * @param books the list of books (not used directly for calculation)
   * @param basePrice the original price before discount
   * @return the discount amount to subtract from the base price
   */
  @Override
  public double applyDiscount(List<Book> books, double basePrice) {
    return basePrice * DISCOUNT_RATE;
  }
}
