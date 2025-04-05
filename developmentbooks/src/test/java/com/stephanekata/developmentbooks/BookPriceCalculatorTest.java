package com.stephanekata.developmentbooks;

import com.stephanekata.developmentbooks.model.Book;
import com.stephanekata.developmentbooks.service.BookPriceCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookPriceCalculatorTest {

	private final BookPriceCalculator calculator = new BookPriceCalculator ();

	@Test
	void shouldReturnPriceWithoutDiscountForSingleBook() {
		List< Book > books = List.of(new Book("Clean Code", "Robert Martin", 2008));
		double price = calculator.calculateTotalPrice(books);

		assertEquals(50.0, price);
	}

}
