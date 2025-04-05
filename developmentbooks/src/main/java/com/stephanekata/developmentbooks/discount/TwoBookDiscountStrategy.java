package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwoBookDiscountStrategy implements DiscountStrategy {

    private static final int REQUIRED_UNIQUE_BOOKS = 2;
    private static final double DISCOUNT_RATE = 0.05;

    @Override
    public boolean isApplicable(List<Book> books) {
        return books.stream().map(Book::title).distinct().count() == REQUIRED_UNIQUE_BOOKS;
    }

    @Override
    public double applyDiscount(List<Book> books, double basePrice) {
        return basePrice * DISCOUNT_RATE;
    }
}
