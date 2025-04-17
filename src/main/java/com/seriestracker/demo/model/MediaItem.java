package com.seriestracker.demo.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Entity;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title Required")
    private String title;
    
    @NotBlank(message = "Status Required")
    private String status; // Completed | Ongoing

    // Default Constructor for JPA
    public MediaItem() {}

    // Super Constructor
    public MediaItem(String title, String status) {
        this.title = title;
        this.status = status;
    }

    // Basic getters and setters for our data

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
