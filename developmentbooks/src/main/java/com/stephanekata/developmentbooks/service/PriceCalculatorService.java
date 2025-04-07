package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.discount.*;
import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.utils.BookGrouper;
import com.stephanekata.developmentbooks.utils.DiscountApplier;
import com.stephanekata.developmentbooks.utils.PriceCalculator;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service responsible for calculating the total price of books by grouping them and applying the
 * best applicable discount strategies.
 *
 * <p>This service delegates grouping to {@link BookGrouper}, price calculation to {@link
 * PriceCalculator}, and discount application to {@link DiscountApplier}.
 */
@Service
public class PriceCalculatorService {

  private final DiscountApplier discountApplier;

  /**
   * Default constructor that initializes the service with a {@link DiscountApplier} containing a
   * predefined order of discount strategies.
   */
  public PriceCalculatorService() {
    this.discountApplier = new DiscountApplier(DiscountStrategyFactory.getOrderedStrategies());
  }

  /**
   * Constructor for dependency injection, primarily for testing or supplying custom discount
   * strategy sets.
   *
   * @param discountApplier the discount applier to use
   */
  public PriceCalculatorService(DiscountApplier discountApplier) {
    this.discountApplier = discountApplier;
  }

  /**
   * Calculates the total discounted price for a list of books. - Handles null or empty input
   * gracefully. - Uses {@link BookGrouper} to group books for maximizing discount. - Applies
   * applicable discount to each group and sums the total.
   *
   * @param books the list of books to calculate price for
   * @return the total price after discounts
   */
  public double calculateTotalPrice(List<Book> books) {
    List<Book> safeBooks = books == null ? Collections.emptyList() : books;
    if (safeBooks.isEmpty()) return 0.0;

    return BookGrouper.groupBooks(safeBooks).stream()
        .mapToDouble(this::calculateDiscountedPriceForGroup)
        .sum();
  }

  /**
   * Calculates the discounted price for a group of books: - Computes the base price using group
   * size. - Applies the most suitable discount via the {@link DiscountApplier}.
   *
   * @param group a list of books in the same discount group
   * @return the final price for the group after applying discount
   */
  private double calculateDiscountedPriceForGroup(List<Book> group) {
    double basePrice = PriceCalculator.calculateBasePrice(group.size());
    double discount = discountApplier.applyDiscount(group, basePrice);
    return basePrice - discount;
  }
}
