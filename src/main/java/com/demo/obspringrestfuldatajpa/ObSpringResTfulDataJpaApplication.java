package com.demo.obspringrestfuldatajpa;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import com.demo.obspringrestfuldatajpa.Repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObSpringResTfulDataJpaApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObSpringResTfulDataJpaApplication.class, args);
        BookRepository bookRepository = context.getBean(BookRepository.class);

        Book book1 = new Book("Stephen King", true, LocalDate.of(1987,11,14), 540, 250.5, "Titanic");
        Book book2 = new Book("J.K. Rowling", true, LocalDate.of(2001,6,20), 540, 416.0, "El señor de los anillos");
        Book book3 = new Book("F. Scott Fitzgerald", true, LocalDate.of(2013,2,27), 1165, 850.5, "La novia");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        System.out.println(bookRepository.findByOnline(true));
    }
}
