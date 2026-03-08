package com.demo.obspringrestfuldatajpa.Controller;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import com.demo.obspringrestfuldatajpa.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // Atributo de inyección de BookService
    private final BookService bookService;

    // Inyecta el bean de BookRepository y asi accedemos a los métodos de la interface
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Logger para el control de errores y mensajes de la aplicación
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    /**
     * {@code @Param} pageable
     * {@code @Return} List of all Books
     */
    @Operation(summary = "List alls books", description = "It retrieves a list of all books registered in the database.")
    @GetMapping
    public ResponseEntity<List<Book>> findAlls(Pageable pageable) {
       log.info("200 OK - Getting all books");
       Page<Book> books = bookService.findAllsBooks(pageable);
       return ResponseEntity.ok(books.getContent());
    }

    /**
     * {@code @Param} id
     * {@code @return} Book by Id
     */
    @Operation(summary = "Get a book by your ID", description = "Returns a book based on the provided ID")
    @GetMapping("/{id}")
    // ResponseEntity - Sirve para devolver un objeto y un estado HTTP
    public ResponseEntity<Book> findById(@PathVariable Long id) {  // -> @PathVariable: Indica que el parámetro id es un valor de la URL
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()){
            log.error("404 Not Found - The book ID does not exist and cannot be obtained.");
            return ResponseEntity.notFound().build();
        }
        log.info("200 OK - Getting book by id: {}", id);
        return book.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * {@code @Param} book
     * {@code @Return} Book created
     */
    // Metodo POST para CREAR un libro y guardarlo en la base de datos
    @Operation(summary = "Create book", description = "Create a new book and save it to the database")
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {  // -> @RequestBody: Indica que el parámetro book es un valor de la URL
        if (book.getId() != null){
            log.error("400 Bad Request - The book ID must be null to create a new book");
            return ResponseEntity.badRequest().build(); // Si existe un id, no se puede crear
        }
        log.info("201 Created - Book created successfully");
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * {@code @Param} book
     * {@code @Return} Update Book
     */
    // Metodo PUT para ACTUALIZAR/MODIFICAR un libro en base de datos
    @Operation(summary = "Update book", description = "Update the data of an existing book")
    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book){
        Optional<Book> existingBook = bookService.findBookById(book.getId());
        // Si el id es null, no se puede actualizar
        if (book.getId() == null){
            log.error("400 Bad Request - The book ID must not be null in order to update a book");
            return ResponseEntity.badRequest().build();
        }
        // Si el libro no existe, no se puede actualizar
        if (existingBook.isEmpty()){
            log.error("404 Not Found - The book ID does not exist in order to update a book");
            return ResponseEntity.notFound().build();
        }
        log.info("200 OK - Book updated successfully");
        Book savedBook = bookService.updateBook(book);
        return ResponseEntity.ok(savedBook);
    }

    /**
     * {@code @Param} id
     * {@code @Return} Book deleted
     */
    // Metodo DELETE para borrar un libro de la base de datos
    @Operation(summary = "Delete a book by your ID", description = "Delete a book of the database")
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()){
            log.error("404 Not Found - The book ID does not exist and cannot be deleted.");
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBookById(id);
        log.info("204 No Content - Book deleted successfully");
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code @Param} pageable
     * {@code @Return} no content
     */
    // Metodo DELETE para borrar todos los libros
    @Operation(summary = "Delete alls books", description = "Delete alls book of the database")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(Pageable pageable){
        Page<Book> pageBook = bookService.findAllsBooks(pageable);
        if (pageBook.isEmpty()){
            log.error("404 Not Found - There are no books to delete..");
            return ResponseEntity.notFound().build();
        }
        log.info("204 No Content - All books deleted");
        bookService.deleteAllsBooks();
        return ResponseEntity.noContent().build();
    }

}
