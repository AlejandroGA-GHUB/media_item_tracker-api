package com.media_item_tracker.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media_item_tracker.demo.model.MediaItem;
import com.media_item_tracker.demo.repository.MediaItemRepository;

import java.util.List;

@RestController
@RequestMapping("/media_item")
public class MediaItemController {

    // Repository used for managing our media item entities.
    private MediaItemRepository mediaItemRepository;

    // Constructor to initialize the MediaItemController with a mediaItemRepository for database interactions.
    public MediaItemController(MediaItemRepository mediaItemRepository) {
        this.mediaItemRepository = mediaItemRepository;
    }
    
    // Get request to get all media items, currently all books and shows.
    @GetMapping
    public ResponseEntity<List<MediaItem>> getAllMediaItems() {
        return ResponseEntity.ok(mediaItemRepository.findAll());
    }

}
