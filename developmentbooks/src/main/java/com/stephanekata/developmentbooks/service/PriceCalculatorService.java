package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.utils.BookGrouper;
import com.stephanekata.developmentbooks.utils.DiscountApplier;
import com.stephanekata.developmentbooks.utils.PriceCalculator;
import com.stephanekata.developmentbooks.discount.*;

import java.util.Arrays;
import java.util.List;

public class PriceCalculatorService {

    private final DiscountApplier discountApplier;

    public PriceCalculatorService() {
    this.discountApplier =
        new DiscountApplier(
            Arrays.asList(new TwoBookDiscountStrategy(), new ThreeBookDiscountStrategy()));
    }

    public PriceCalculatorService(DiscountApplier discountApplier) {
        this.discountApplier = discountApplier;
    }

    public double calculateTotalPrice(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        List<List<Book>> groupedBooks = BookGrouper.groupBooks(books);
        double total = 0.0;

        for (List<Book> group : groupedBooks) {
            double basePrice = PriceCalculator.calculateBasePrice(group.size());
            double discount = discountApplier.applyDiscount(group, basePrice);
            total += (basePrice - discount);
        }

        return total;
    }
}
