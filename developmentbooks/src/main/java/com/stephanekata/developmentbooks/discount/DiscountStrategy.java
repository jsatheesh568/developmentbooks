package com.stephanekata.developmentbooks.discount;

import com.stephanekata.developmentbooks.model.Book;

import java.util.List;

public interface DiscountStrategy {
    boolean isApplicable( List <Book> books);
    double applyDiscount( List< Book > books, double basePrice);
}
