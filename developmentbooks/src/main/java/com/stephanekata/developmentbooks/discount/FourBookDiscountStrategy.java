package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;

import java.util.List;

public class FourBookDiscountStrategy implements DiscountStrategy {
    @Override
    public boolean isApplicable( List < Book > books) {
        return books.stream().map(Book::title).distinct().count() == 4;
    }

    @Override
    public double applyDiscount(List<Book> books, double basePrice) {
        return basePrice * 0.20;
    }
}
