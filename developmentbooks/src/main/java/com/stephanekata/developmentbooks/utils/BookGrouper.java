package com.stephanekata.developmentbooks.utils;

import com.stephanekata.developmentbooks.model.Book;

import java.util.*;

public class BookGrouper {

    public static List<List<Book>> groupBooks(List<Book> books) {
        Map<String, Integer> titleCountMap = countBooksByTitle(books);
        List<List<Book>> groupedBooks = new ArrayList<>();

        while (!titleCountMap.isEmpty()) {
            List<Book> group = createGroupFromRemainingBooks(titleCountMap);
            groupedBooks.add(group);
        }
        optimizeGroupsForBetterDiscount(groupedBooks);
        return groupedBooks;
    }
    private static Map<String, Integer> countBooksByTitle(List<Book> books) {
        Map<String, Integer> titleCountMap = new HashMap<>();

        books.stream()
                .filter(BookGrouper::isValidBook)
                .forEach(book -> titleCountMap.merge(book.title(), 1, Integer::sum));

        return titleCountMap;
    }

    private static boolean isValidBook(Book book) {
        return book != null && book.title() != null && !book.title().isBlank();
    }

    private static List<Book> createGroupFromRemainingBooks(Map<String, Integer> titleCountMap) {
        List<Book> group = new ArrayList<>();

        for (String title : new ArrayList<>(titleCountMap.keySet())) {
            group.add(new Book(title, "", 0));
            decrementOrRemove(title, titleCountMap);
        }

        return group;
    }

    private static void decrementOrRemove(String title, Map<String, Integer> map) {
        map.computeIfPresent(title, (k, v) -> v - 1);
        if (map.get(title) != null && map.get(title) <= 0) {
            map.remove(title);
        }
    }

    private static void optimizeGroupsForBetterDiscount(List<List<Book>> groupedBooks) {
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
