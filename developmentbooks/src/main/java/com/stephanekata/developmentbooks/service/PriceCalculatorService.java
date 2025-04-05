package com.stephanekata.developmentbooks.service;

import com.stephanekata.developmentbooks.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceCalculatorService {

    private static final double BASE_PRICE = 50.0;

    public double calculateTotalPrice(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return 0.0;
        }
        
        Map<String, Integer> titleCounts = new HashMap<>();
        for (Book book : books) {
            titleCounts.merge(book.title(), 1, Integer::sum);
        }

        double total = 0.0;

        while (!titleCounts.isEmpty()) {

            List<String> uniqueTitles = new ArrayList<>(titleCounts.keySet());
            int uniqueCount = uniqueTitles.size();

            double discount = getDiscountRate(uniqueCount);

            total += uniqueCount * BASE_PRICE * (1 - discount);

            for (String title : uniqueTitles) {
                int count = titleCounts.get(title);
                if (count == 1) {
                    titleCounts.remove(title);
                } else {
                    titleCounts.put(title, count - 1);
                }
            }
        }

        return total;
    }

    private double getDiscountRate(int uniqueCount) {
        return switch (uniqueCount) {
            case 2 -> 0.05;
            case 3 -> 0.10;
            default -> 0.0;
        };
    }
}
