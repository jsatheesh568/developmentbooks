package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    /**
     * Calculates the total price of the given books without any discount logic for now.
     */
    public double calculateTotalPrice( List < Book > books) {
        return 50.0;
    }
}
