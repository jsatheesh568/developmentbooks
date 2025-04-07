package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import java.util.List;
import org.springframework.stereotype.Component;

/** Strategy that applies a 5% discount if the list contains exactly two unique book titles. */
@Component
public class TwoBookDiscountStrategy implements DiscountStrategy {

  private static final int REQUIRED_UNIQUE_BOOKS = 2;
  private static final double DISCOUNT_RATE = 0.05;

  /**
   * Checks whether this discount strategy is applicable. It applies only if there are exactly two
   * different book titles.
   *
   * @param books the list of books in the cart
   * @return true if exactly two unique titles exist, false otherwise
   */
  @Override
  public boolean isApplicable(List<Book> books) {
    if (books == null || books.isEmpty()) return false;
    return books.stream().map(Book::title).distinct().count() == REQUIRED_UNIQUE_BOOKS;
  }

  /**
   * Applies the 5% discount on the base price.
   *
   * @param books the list of books (not used in calculation, just validation)
   * @param basePrice the total price before discount
   * @return the discount amount
   */
  @Override
  public double applyDiscount(List<Book> books, double basePrice) {
    return basePrice * DISCOUNT_RATE;
  }
}
