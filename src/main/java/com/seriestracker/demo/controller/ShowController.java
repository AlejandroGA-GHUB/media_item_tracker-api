package com.seriestracker.demo.controller;

import com.seriestracker.demo.exceptions.ShowNotFoundException;
import com.seriestracker.demo.model.Show;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.seriestracker.demo.repository.ShowRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    
    // Repository used for managing our show entities.
    private final ShowRepository showRepository;

    // Constructor to initialize the ShowController with a showRepository for database interactions.
    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // Post request to save a book.
    @PostMapping
    public ResponseEntity<Show> saveShow(@Valid @RequestBody Show show) {
        Show savedShow = showRepository.save(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow); // Returns a 201 Status (Successful Creation) and shows the savedShow within the body.
    }

    // Get request to get all shows.
    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showRepository.findAll()); // Returns a 200 Ok status and the saved shows in the list. 
    }

    // Get request to get a specific show by id if saved.
    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {

        Show show = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("No show with ID: " + id + " found"));

        return ResponseEntity.ok(show); // Returns a 200 Ok status and the saved show tied to the given id.
    }

    // Get request to get all shows that match the passed in keyword.
    @GetMapping("/search")
    public ResponseEntity<List<Show>> searchInShows(@RequestParam String keyword) {

        List<Show> shows = showRepository.findByTitleContainingIgnoreCase(keyword);

        return shows.size() > 0 ? ResponseEntity.ok(shows) : ResponseEntity.noContent().build(); // Returns 200 Ok status and the corresponding shows to keyword in a list | Returns a 204 no content status
    }

    // Patch request to update whichever non-null field was passed in for updates.
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Show> updateShowData(@RequestBody Show show, @PathVariable Long id) {

        Show existingShow = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("No show with ID: " + id + " found"));

        if (show.getStatus() != null) { existingShow.setStatus(show.getStatus()); }
        if (show.getTitle() != null) { existingShow.setTitle(show.getTitle()); }
        if (show.getCurrentEpisode() != null && show.getCurrentEpisode() > 0) { existingShow.setCurrentEpisode(show.getCurrentEpisode()); }

        return ResponseEntity.ok(showRepository.save(existingShow)); // Returns a 200 Ok (Successful Save)
    }

    // Patch request to update only the episode count, the most used use case.
    @PatchMapping("/{id}/episode")
    public ResponseEntity<Show> updateCurrentEpisodeCount(@RequestParam @Min(1) Integer newEpisodeCount, @PathVariable Long id) {

        Show existingShow = showRepository.findById(id).orElseThrow(() -> new ShowNotFoundException("No show with ID: " + id + " found"));

        existingShow.setCurrentEpisode(newEpisodeCount);

        return ResponseEntity.ok(showRepository.save(existingShow));
    }
    
    // Delete request to delete a show by its id.
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShowById(@PathVariable Long id) {
        
        if (!showRepository.existsById(id)) {
            throw new ShowNotFoundException("No show with ID: " + id + " found");
        }

        showRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Returns a 204 no content status (Successful Delete)
    }
    
}
