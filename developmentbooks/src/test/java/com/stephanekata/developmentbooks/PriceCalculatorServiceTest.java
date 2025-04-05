package com.stephanekata.developmentbooks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephanekata.developmentbooks.controller.PriceCalculatorController;
import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.PriceCalculatorService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class PriceCalculatorServiceTest {

  @Test
  public void testSingleBookReturnsBasePrice() {
    PriceCalculatorService priceCalculatorService = new PriceCalculatorService();
    List<Book> books = List.of(new Book("Clean Code", "Robert Martin", 2008));

    double result = priceCalculatorService.calculateTotalPrice(books);

    assertEquals(50.0, result);
  }
}
