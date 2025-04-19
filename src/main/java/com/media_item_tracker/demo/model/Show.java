package com.media_item_tracker.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "shows")
public class Show extends MediaItem {
    
    @Column(name = "current_episode")
    @Min(value = 1, message = "New Episode Counter Must Be Atleast 1")
    private Integer currentEpisode;

    // Default constructor for JPA
    public Show() {} 

    // Show Constructor
    public Show(String title, String status, Integer currentEpisode) {
        super(title, status);
        this.currentEpisode = currentEpisode;
    }

    // Current episode getter
    public Integer getCurrentEpisode() {
        return currentEpisode;
    }

    // Current episode setter
    public void setCurrentEpisode(Integer currentEpisode) {
        this.currentEpisode = currentEpisode;
    }
}
