package com.media_item_tracker.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media_item_tracker.demo.model.MediaItem;

public interface MediaItemRepository extends JpaRepository<MediaItem, Long> {

    // Custom queries below

}
    



