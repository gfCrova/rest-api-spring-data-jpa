package com.demo.obspringrestfuldatajpa.Controller;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import com.demo.obspringrestfuldatajpa.Repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // Atributo de inyección de BookRepository
    private final BookRepository bookRepository;
    // Logger para el control de errores y mensajes de la aplicación
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

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
       log.info("200 OK - Getting all books");
       return bookRepository.findAll();
    }

    /**
     * <a href="http://localhost:8080/api/books/{id}">http://localhost:8080/api/books/{id}</a>
     * @param id
     * @return Book by Id
     */
    @GetMapping("api/books/{id}")
    // ResponseEntity - Sirve para devolver un objeto y un estado HTTP
    public ResponseEntity<Book> findById(@PathVariable Long id) {  // -> @PathVariable: Indica que el parámetro id es un valor de la URL
        log.info("200 OK - Getting book by id: {}", id);
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param book
     * @return Book created
     */
    // Metodo POST para CREAR un libro y guardarlo en la base de datos
    @PostMapping("api/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {  // -> @RequestBody: Indica que el parámetro book es un valor de la URL
        if (book.getId() != null){
            log.error("400 Bad Request - The book ID must be null to create a new book");
            return ResponseEntity.badRequest().build(); // Si existe un id, no se puede crear
        }
        log.info("201 Created - Book created successfully");
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    // Metodo PUT para ACTUALIZAR/MODIFICAR un libro en base de datos
    @PutMapping("api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        // Si el id es null, no se puede actualizar
        if (book.getId() == null){
            log.error("400 Bad Request - The book ID must not be null in order to update a book");
            return ResponseEntity.badRequest().build();
        }
        // Si el libro no existe, no se puede actualizar
        if (!bookRepository.existsById(book.getId())){
            log.error("404 Not Found - The book ID does not exist in order to update a book");
            return ResponseEntity.notFound().build();
        }
        log.info("200 OK - Book updated successfully");
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * @Param id
     * @return Book deleted
     */
    // Metodo DELETE para borrar un libro de la base de datos
    @DeleteMapping("api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            log.error("404 Not Found - The book ID does not exist and cannot be deleted.");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        log.info("204 No Content - Book deleted successfully");
        return ResponseEntity.noContent().build();
    }

    // Metodo DELETE para borrar todos los libros
    @DeleteMapping("api/books")
    public ResponseEntity<Void> deleteAll(){
        if (bookRepository.findAll().isEmpty()){
            log.error("404 Not Found - There are no books to delete..");
            return ResponseEntity.notFound().build();
        }
        log.info("204 No Content - All books deleted");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
