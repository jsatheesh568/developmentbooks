package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThreeBookDiscountStrategy implements DiscountStrategy {

    private static final int REQUIRED_UNIQUE_BOOKS = 3;
    private static final double DISCOUNT_RATE = 0.10;

    @Override
    public boolean isApplicable(List<Book> books) {
        return books.stream().map(Book::title).distinct().count() == REQUIRED_UNIQUE_BOOKS;
    }

    @Override
    public double applyDiscount(List<Book> books, double basePrice) {
        return basePrice * DISCOUNT_RATE;
    }
}
