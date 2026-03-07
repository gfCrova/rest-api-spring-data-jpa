package com.demo.obspringrestfuldatajpa.Controller;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import com.demo.obspringrestfuldatajpa.Repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    // Atributo de inyección de BookRepository
    private final BookRepository bookRepository;

    // Inyecta el bean de BookRepository y asi accedemos a los métodos de la interface
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * <a href="http://localhost:8080/api/books">http://localhost:8080/api/books</a>
     * @return List of all Books
     */
    @GetMapping("api/books")
    public List<Book> findAlls() {
       return bookRepository.findAll();
    }

    /**
     * <a href="http://localhost:8080/api/books/{id}">http://localhost:8080/api/books/{id}</a>
     * @param id
     * @return Book by Id
     */
    @GetMapping("api/books/{id}")
    public Book findById(@PathVariable Long id) {  // -> @PathVariable: Indica que el parámetro id es un valor de la URL
       return bookRepository.findById(id).orElse(null);
    }

    // Metodo POST para crear un libro
    @PostMapping("api/books/create")
    public Book create(@RequestBody Book book) {  // -> @RequestBody: Indica que el parámetro book es un valor de la URL
       return bookRepository.save(book);
    }
}
