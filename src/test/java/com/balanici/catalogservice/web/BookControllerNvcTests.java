package com.balanici.catalogservice.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.balanici.catalogservice.exception.BookNotFoundException;
import com.balanici.catalogservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerNvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "1231231230";
        given(bookService.viewBookDetails(isbn))
            .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/" + isbn))
            .andExpect(status().isNotFound());
    }

    @Test
    void getBookList() {
    }

    @Test
    void getByIsbn() {
    }

    @Test
    void addBookToCatalog() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void editBook() {
    }
}
