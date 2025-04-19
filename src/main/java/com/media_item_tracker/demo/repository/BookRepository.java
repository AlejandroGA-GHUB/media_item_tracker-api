package com.media_item_tracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media_item_tracker.demo.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Custom queries below

    // Used in searching for saved books via a keyword
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
