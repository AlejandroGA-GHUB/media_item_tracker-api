package com.seriestracker.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.seriestracker.demo.model.Book;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seriestracker.demo.repository.BookRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;
import com.seriestracker.demo.exceptions.BookNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {
    
    // Repository used for managing our book entities.
    private final BookRepository bookRepository;

    // Constructor to initialize the BookController with a bookRepository for database interactions.
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Post request to save a book.
    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook); // Returns a 201 status (Successful Creation) and the saved book within the body.
    }

    // Get request to retrieve all saved books.
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll()); // Returns a 200 Ok status and the saved books in a list.
    }

    // Get request to retrieve a saved book by its id.
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("No book with ID " + id + " found"));

        return ResponseEntity.ok(book); // Returns a 200 Ok status and the saved book tied to the given id.
    }

    // Get request to find books based on a keyword
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchInBooks(@RequestParam String keyword) {

        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(keyword);

        return books.size() > 0 ? ResponseEntity.ok(books) : ResponseEntity.noContent().build(); // Returns a 200 Ok status and the books found via keyword in a list | Returns a 204 no content status.
    }

    // Update request to update all non-null data to a saved book.
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookData(@RequestBody Book book, @PathVariable Long id) {

        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        
        // Updates only if the new data isn't null.
        if (book.getStatus() != null) { existingBook.setStatus(book.getStatus()); }
        if (book.getTitle() != null) { existingBook.setTitle(book.getTitle()); }
        if (book.getCurrentChapter() != null && book.getCurrentChapter() > 0) { existingBook.setCurrentChapter(book.getCurrentChapter()); }

        return ResponseEntity.ok(bookRepository.save(existingBook)); // Returns 200 Ok (Successful Save)
    }

    // Update request to only update chapters, the most used use case.
    @PatchMapping("/{id}/chapter")
    public ResponseEntity<Book> updateCurrentBookChapter(@RequestParam @Min(1) Integer newChapterCount, @PathVariable Long id) {

        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        existingBook.setCurrentChapter(newChapterCount);

        return ResponseEntity.ok(bookRepository.save(existingBook)); // Returns 200 Ok (Successful Save)
    }

    // Delete request to delete the selected book via its id.
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID: " + id + " not found");
        }

        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content (Successful Delete)
    }
}
