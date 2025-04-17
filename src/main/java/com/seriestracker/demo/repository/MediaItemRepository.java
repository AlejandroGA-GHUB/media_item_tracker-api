package com.seriestracker.demo.repository;

import com.seriestracker.demo.model.MediaItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {

    // Custom queries below

}
    



