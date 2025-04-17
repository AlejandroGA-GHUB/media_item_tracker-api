package com.seriestracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seriestracker.demo.model.*;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long>{
    
    // Custom queries below

    // Used in searching for saved shows via a keyword
    List<Show> findByTitleContainingIgnoreCase(String keyword);
}
