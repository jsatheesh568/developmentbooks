package com.stephanekata.developmentbooks.utils;


/**
 * Utility class to validate request parameters for Book API.
 *
 * Author: Satheeshkumar Subramanian
 */
public class RequestValidator {

    /**
     * Validates the author and year parameters.
     *
     * @param author the author query parameter
     * @param year   the year query parameter
     * @throws IllegalArgumentException if parameters are invalid
     */
    public static void validateBookQueryParams(String author, String year) {
        if (author != null && author.isBlank()) {
            throw new IllegalArgumentException("Author parameter cannot be empty");
        }

        if (year != null) {
            if (year.isBlank()) {
                throw new IllegalArgumentException("Year parameter cannot be empty");
            } else if (!year.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid year format");
            }
        }
    }
}

