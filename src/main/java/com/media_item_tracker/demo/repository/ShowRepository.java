package com.media_item_tracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media_item_tracker.demo.model.*;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long>{
    
    // Custom queries below

    // Used in searching for saved shows via a keyword
    List<Show> findByTitleContainingIgnoreCase(String keyword);
}
