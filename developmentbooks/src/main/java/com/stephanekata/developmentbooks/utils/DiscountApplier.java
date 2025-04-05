package com.stephanekata.developmentbooks.utils;

import com.stephanekata.developmentbooks.discount.DiscountStrategy;
import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountApplier {

    private final List< DiscountStrategy > discountStrategies;

    public DiscountApplier( List <DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    public double applyDiscount( List< Book > group, double basePrice) {
        for (DiscountStrategy strategy : discountStrategies) {
            if (strategy.isApplicable(group)) {
                return strategy.applyDiscount(group, basePrice);
            }
        }
        return 0.0;
    }
}
