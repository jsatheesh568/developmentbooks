package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private static final double BASE_PRICE = 50.0;

    /**
     * Calculates the total price of the given books.
     * Currently applies base price per book without discounts.
     */
    public double calculateTotalPrice(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        return books.stream()
                .mapToDouble(this::getPriceForBook)
                .sum();
    }

    /**
     * Gets the price for a single book.
     * Future logic like discounts per title can be applied here.
     */
    private double getPriceForBook(Book book) {
        return BASE_PRICE;
    }
}
