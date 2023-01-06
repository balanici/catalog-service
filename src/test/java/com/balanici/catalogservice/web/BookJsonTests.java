package com.balanici.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.balanici.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> jacksonTester;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90);
        var jsonContent = jacksonTester.write(book);

        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
              "isbn": "1234567890",
              "title": "Title",
              "author": "Author",
              "price": 9.90
            }
            """;

        assertThat(jacksonTester.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90));
    }

}
