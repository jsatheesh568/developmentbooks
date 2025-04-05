package com.stephanekata.developmentbooks;

import com.stephanekata.developmentbooks.controller.BookController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest( BookController.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllBooks() throws Exception {
        mockMvc.perform ( MockMvcRequestBuilders.get("/api/v1/bookstore/books") )
                .andExpect ( MockMvcResultMatchers.status ().isOk () )
                .andExpect ( MockMvcResultMatchers.jsonPath ( "$", Matchers.hasSize ( 5 ) ) );
    }
}
