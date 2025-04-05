package com.stephanekata.developmentbooks.utils;

public class PriceCalculator {
    public static final double BASE_PRICE = 50.0;

    public static double calculateBasePrice(int count) {
        return count * BASE_PRICE;
    }
}
