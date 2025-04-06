package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;

import java.util.List;

public class FiveBookDiscountStrategy implements DiscountStrategy {
    @Override
    public boolean isApplicable( List < Book > books) {
        return books.stream().map(Book::title).distinct().count() == 5;
    }

    @Override
    public double applyDiscount(List<Book> books, double basePrice) {
        return basePrice * 0.25;
    }
}

