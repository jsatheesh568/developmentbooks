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

        double baseTotal = books.size() * BASE_PRICE;
        double discount = calculateDiscount(books);

        return baseTotal - discount;
    }

    private double calculateDiscount(List<Book> books) {
        long uniqueBooks = books.stream()
                .map(Book::title)
                .distinct()
                .count();

        if (uniqueBooks == 2) {
            return books.size() * BASE_PRICE * 0.05; // 5% discount
        }

        return 0.0;
    }

}
