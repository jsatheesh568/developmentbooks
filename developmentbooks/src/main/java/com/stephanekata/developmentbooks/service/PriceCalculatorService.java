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
        long uniqueBooksCount = books.stream()
                .map(Book::title)
                .distinct()
                .count();

        double totalPrice = books.size() * BASE_PRICE;

        if (uniqueBooksCount == 2) {
            totalPrice *= 0.95;
        }

        return totalPrice;
    }
}
