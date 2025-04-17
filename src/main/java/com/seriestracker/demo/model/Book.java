package com.seriestracker.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "books")
public class Book extends MediaItem {
    
    @Column(name = "current_chapter")
    @Min(value = 1, message = "New Chapter Counter Must Be Atleast 1")
    private Integer currentChapter;

    // Default constructor for JPA
    public Book() {} 

    // Book Constructor
    public Book(String title, String status, Integer currentChapter) {
        super(title, status);
        this.currentChapter = currentChapter;
    }

    // Current chapter getter
    public Integer getCurrentChapter() {
        return currentChapter;
    }

    // Current chapter setter
    public void setCurrentChapter(Integer currentChapter) {
        this.currentChapter = currentChapter;
    }

}
