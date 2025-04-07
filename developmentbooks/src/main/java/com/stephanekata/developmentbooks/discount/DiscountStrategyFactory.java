package com.stephanekata.developmentbooks.discount;

import java.util.List;

/**
 * Factory class for providing a list of discount strategies in a specific order.
 *
 * <p>The strategies are ordered from the most significant (highest discount for more books) to the
 * least significant. This ensures that the best possible discount is attempted first.
 */
public class DiscountStrategyFactory {

  /**
   * Returns a list of {@link DiscountStrategy} implementations ordered by priority, starting from
   * the strategy with the highest discount.
   *
   * @return an ordered list of discount strategies
   */
  public static List<DiscountStrategy> getOrderedStrategies() {
    return List.of(
        new FiveBookDiscountStrategy(),
        new FourBookDiscountStrategy(),
        new ThreeBookDiscountStrategy(),
        new TwoBookDiscountStrategy());
  }
}
