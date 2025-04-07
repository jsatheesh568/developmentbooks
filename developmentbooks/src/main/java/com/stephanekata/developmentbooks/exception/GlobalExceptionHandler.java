package com.stephanekata.developmentbooks.exception;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Global exception handler to manage application-wide exception responses. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles invalid number format exceptions, typically from parsing errors.
   *
   * @param ex the NumberFormatException thrown
   * @return a 400 Bad Request response with a clear error message
   */
  @ExceptionHandler(NumberFormatException.class)
  public ResponseEntity<Map<String, String>> handleInvalidYear(NumberFormatException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", "Invalid year format"));
  }

  /**
   * Handles IllegalArgumentExceptions thrown due to invalid inputs.
   *
   * @param ex the IllegalArgumentException thrown
   * @return a 400 Bad Request response with the exception message
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
  }
}
