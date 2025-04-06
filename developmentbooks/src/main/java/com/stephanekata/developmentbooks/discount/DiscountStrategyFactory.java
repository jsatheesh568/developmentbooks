package com.stephanekata.developmentbooks.discount;

import java.util.List;

public class DiscountStrategyFactory {
    public static List <DiscountStrategy> getOrderedStrategies() {
        return List.of(
                new FiveBookDiscountStrategy(),
                new FourBookDiscountStrategy(),
                new ThreeBookDiscountStrategy(),
                new TwoBookDiscountStrategy()
        );
    }
}
