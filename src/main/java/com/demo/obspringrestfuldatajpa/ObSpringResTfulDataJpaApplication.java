package com.demo.obspringrestfuldatajpa;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Books API",
                version = "1.0",
                description = "REST API for managing a collection of books. It supports CRUD operations such as creating, retrieving, updating, and deleting books."
        )
)
@SpringBootApplication
public class ObSpringResTfulDataJpaApplication {

    public static void main(String[] args) {

        //ApplicationContext context =
        SpringApplication.run(ObSpringResTfulDataJpaApplication.class, args);
        //BookRepository bookRepository = context.getBean(BookRepository.class);
        //BookService bookService = context.getBean(BookService.class);

        //Book book1 = new Book("Stephen King", true, LocalDate.of(1987,11,14), 540, 250.5, "Titanic");
        //Book book2 = new Book("J.K. Rowling", true, LocalDate.of(2001,6,20), 540, 416.0, "El señor de los anillos");
        //Book book3 = new Book("F. Scott Fitzgerald", true, LocalDate.of(2013,2,27), 1165, 850.5, "La novia");

        //bookService.createBook(book1);
        //bookService.createBook(book2);
        //bookService.createBook(book3);

        //System.out.println(bookRepository.findByOnline(true));
    }
}
