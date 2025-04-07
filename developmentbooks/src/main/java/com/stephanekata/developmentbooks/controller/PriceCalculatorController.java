package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * PriceCalculatorController handles REST API endpoints related to price calculation for a list of
 * books, including applying any available discounts or promotions.
 *
 * <p>Author: Satheeshkumar Subramanian Version: 1.0
 */
@RestController
@RequestMapping("/api/v1/bookprice")
@Tag(
    name = "Price Calculator API",
    description = "Calculate total price with discounts for selected books")
public class PriceCalculatorController {

  private final PriceCalculatorService priceCalculatorService;

  public PriceCalculatorController(PriceCalculatorService priceCalculatorService) {
    this.priceCalculatorService = priceCalculatorService;
  }

  /**
   * Calculates the total price for a given list of books. Discounts, bundles, or promotional
   * pricing may be applied depending on logic in the service layer.
   *
   * @param books List of books for which the total price needs to be calculated.
   * @return ResponseEntity containing the calculated total price.
   */
  @PostMapping("/calculate")
  @Operation(
      summary = "Calculate total price",
      description = "Calculates total price of books considering discounts for combinations")
  public ResponseEntity<Double> calculatePrice(@RequestBody List<Book> books) {
    double total = priceCalculatorService.calculateTotalPrice(books);
    return ResponseEntity.ok(total);
  }
}
