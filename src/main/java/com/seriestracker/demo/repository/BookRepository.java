package com.seriestracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seriestracker.demo.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Custom queries below

    // Used in searching for saved books via a keyword
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
