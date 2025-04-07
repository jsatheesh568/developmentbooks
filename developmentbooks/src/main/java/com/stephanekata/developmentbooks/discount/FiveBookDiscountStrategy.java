package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import java.util.List;

/** Applies a 25% discount when there are exactly 5 distinct books in the purchase. */
public class FiveBookDiscountStrategy implements DiscountStrategy {

  private static final int REQUIRED_BOOK_COUNT = 5;
  private static final double DISCOUNT_RATE = 0.25;

  /**
   * Checks if this discount strategy can be applied. Applicable only when the number of distinct
   * book titles is exactly 5.
   *
   * @param books the list of books being purchased
   * @return true if exactly 5 distinct books are present, false otherwise
   */
  @Override
  public boolean isApplicable(List<Book> books) {
    return books.stream().map(Book::title).distinct().count() == REQUIRED_BOOK_COUNT;
  }

  /**
   * Applies a 25% discount to the base price.
   *
   * @param books the list of books (not directly used for price calculation)
   * @param basePrice the original price before discount
   * @return the discount amount to subtract from the base price
   */
  @Override
  public double applyDiscount(List<Book> books, double basePrice) {
    return basePrice * DISCOUNT_RATE;
  }
}
