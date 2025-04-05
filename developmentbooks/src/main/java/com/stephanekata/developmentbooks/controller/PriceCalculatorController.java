package com.stephanekata.developmentbooks.controller;

import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import com.stephanekata.developmentbooks.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookprice")
public class PriceCalculatorController {

  private final PriceCalculatorService priceCalculatorService;

  public PriceCalculatorController( PriceCalculatorService priceCalculatorService ) {
    this.priceCalculatorService = priceCalculatorService;
  }

  @PostMapping("/calculate")
  public ResponseEntity<Double> calculatePrice(@RequestBody List<Book> books) {
    double total = priceCalculatorService.calculateTotalPrice(books);
    return ResponseEntity.ok(total);
  }
}
