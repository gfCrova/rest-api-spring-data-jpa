package com.demo.obspringrestfuldatajpa.Service;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAllsBooks(Pageable pageable);
    Optional<Book> findBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(Long id);
    void deleteAllsBooks();
}
