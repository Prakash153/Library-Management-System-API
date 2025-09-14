package com.Prakash.LibraryManagement.controllers;

import com.Prakash.LibraryManagement.dtos.BookDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO newBook) {
        BookDTO createdBook = bookService.addBook(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }


    @GetMapping("/searchBook")
    public ResponseEntity<List<BookDTO>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String isbn) {

        return ResponseEntity.ok(bookService.searchBooks(title, author, genre, isbn));
    }


    @GetMapping("/{bookId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getBookTransactions(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookTransactions(bookId));
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookDTO updatedBook) {
        return ResponseEntity.ok(bookService.updateBook(bookId, updatedBook));
    }



    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}