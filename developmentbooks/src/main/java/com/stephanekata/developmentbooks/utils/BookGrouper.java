package com.stephanekata.developmentbooks.utils;

import com.stephanekata.developmentbooks.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookGrouper {

    public static List< List <Book> > groupBooks( List<Book> books) {
        Map <String, Integer> titleCounts = new HashMap <> ();
        for (Book book : books) {
            titleCounts.merge(book.title(), 1, Integer::sum);
        }

        List<List<Book>> groupedBooks = new ArrayList <> ();

        while (!titleCounts.isEmpty()) {
            List<Book> group = new ArrayList<>();

            for (String title : new ArrayList<>(titleCounts.keySet())) {
                group.add(new Book (title, "", 0));
                titleCounts.computeIfPresent(title, (k, v) -> v - 1);
                if (titleCounts.get(title) == 0) {
                    titleCounts.remove(title);
                }
            }

            groupedBooks.add(group);
        }

        return groupedBooks;
    }
}
