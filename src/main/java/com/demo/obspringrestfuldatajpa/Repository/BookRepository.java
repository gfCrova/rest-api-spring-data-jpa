package com.demo.obspringrestfuldatajpa.Repository;

import com.demo.obspringrestfuldatajpa.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/*
 * -> @Repository: Marca la interface como un repositorio que se encarga de la persistencia de datos
 * -> @JpaRepository: Proporciona los métodos para la persistencia de datos (CRUD)
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByOnline(boolean online);
}
