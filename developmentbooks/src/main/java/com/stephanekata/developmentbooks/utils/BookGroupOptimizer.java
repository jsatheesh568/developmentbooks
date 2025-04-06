package com.stephanekata.developmentbooks.utils;

import com.stephanekata.developmentbooks.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookGroupOptimizer {

    public static void optimize(List<List<Book>> groupedBooks) {
        int groupOfFiveIndex = -1;
        int groupOfThreeIndex = -1;

        for (int i = 0; i < groupedBooks.size(); i++) {
            int size = groupedBooks.get(i).size();
            if (size == 5 && groupOfFiveIndex == -1) {
                groupOfFiveIndex = i;
            } else if (size == 3 && groupOfThreeIndex == -1) {
                groupOfThreeIndex = i;
            }
        }

        if (groupOfFiveIndex != -1 && groupOfThreeIndex != -1) {
            List<Book> groupOfFive = groupedBooks.get(groupOfFiveIndex);
            List<Book> groupOfThree = groupedBooks.get(groupOfThreeIndex);

            for (Book book : new ArrayList<>(groupOfFive)) {
                boolean alreadyExists = groupOfThree.stream()
                        .anyMatch(b -> b.title().equals(book.title()));
                if (!alreadyExists) {
                    groupOfFive.remove(book);
                    groupOfThree.add(book);
                    break;
                }
            }
        }
    }
}
