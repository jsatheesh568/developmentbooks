package com.stephanekata.developmentbooks.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (NumberFormatException.class)
    public ResponseEntity< Map <String, String> > handleInvalidYear( NumberFormatException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid year format"));
    }
}
