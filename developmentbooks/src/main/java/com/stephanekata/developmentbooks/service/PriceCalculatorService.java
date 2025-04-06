package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.utils.BookGrouper;
import com.stephanekata.developmentbooks.utils.DiscountApplier;
import com.stephanekata.developmentbooks.utils.PriceCalculator;
import com.stephanekata.developmentbooks.discount.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PriceCalculatorService {

    private final DiscountApplier discountApplier;

    public PriceCalculatorService() {
        this.discountApplier = new DiscountApplier(DiscountStrategyFactory.getOrderedStrategies());
    }

    public PriceCalculatorService(DiscountApplier discountApplier) {
        this.discountApplier = discountApplier;
    }

    public double calculateTotalPrice(List<Book> books) {
        List<Book> safeBooks = books == null ? Collections.emptyList() : books;
        if (safeBooks.isEmpty()) return 0.0;

        return BookGrouper.groupBooks(safeBooks).stream()
                .mapToDouble(this::calculateDiscountedPriceForGroup)
                .sum();
    }

    private double calculateDiscountedPriceForGroup(List<Book> group) {
        double basePrice = PriceCalculator.calculateBasePrice(group.size());
        double discount = discountApplier.applyDiscount(group, basePrice);
        return basePrice - discount;
    }
}
