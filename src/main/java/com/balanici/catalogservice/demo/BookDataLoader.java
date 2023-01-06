package com.balanici.catalogservice.demo;

import com.balanici.catalogservice.domain.Book;
import com.balanici.catalogservice.repository.BookRepository;
import java.util.List;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        Book book1 = Book.of("1234567891", "Nothern Lights", "Lyra Silverstar", 9.90);
        Book book2 = Book.of("1234567892", "Polar Journey", "Igorek Polarson", 12.90);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.saveAll(List.of(book1, book2));
    }
}
