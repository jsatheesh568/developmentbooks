package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;

import java.util.List;

public class FourBookDiscountStrategy implements DiscountStrategy {

    private static final int REQUIRED_BOOK_COUNT = 4;
    private static final double DISCOUNT_RATE = 0.20;
    @Override
    public boolean isApplicable( List < Book > books) {
        return books.stream().map(Book::title).distinct().count() == REQUIRED_BOOK_COUNT;
    }

    @Override
    public double applyDiscount(List<Book> books, double basePrice) {
        return basePrice * DISCOUNT_RATE;
    }
}
