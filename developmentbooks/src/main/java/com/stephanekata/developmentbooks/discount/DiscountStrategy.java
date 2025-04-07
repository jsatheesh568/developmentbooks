package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import java.util.List;

/**
 * The {@code DiscountStrategy} interface defines the contract for implementing various discount
 * strategies on a collection of books.
 *
 * <p>Each strategy decides if it can be applied based on the given list of books, and calculates
 * the discount accordingly.
 */
public interface DiscountStrategy {

  /**
   * Determines whether this discount strategy can be applied to the provided list of books.
   *
   * @param books the list of books to evaluate
   * @return {@code true} if the strategy is applicable, {@code false} otherwise
   */
  boolean isApplicable(List<Book> books);

  /**
   * Applies the discount strategy to the given list of books and returns the discounted price based
   * on the base price.
   *
   * @param books the list of books to apply the discount on
   * @param basePrice the original total price before discount
   * @return the total price after applying the discount
   */
  double applyDiscount(List<Book> books, double basePrice);
}
