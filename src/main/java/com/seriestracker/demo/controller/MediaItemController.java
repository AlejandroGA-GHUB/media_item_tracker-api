package com.seriestracker.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seriestracker.demo.model.MediaItem;

import java.util.List;

import com.seriestracker.demo.repository.MediaItemRepository;

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
