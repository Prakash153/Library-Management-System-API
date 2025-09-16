package com.Prakash.LibraryManagement.controllers;

import com.Prakash.LibraryManagement.dtos.LibrarianDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.services.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
@RequiredArgsConstructor
public class LibrarianController {

    private final LibrarianService librarianService;

    //  Register a new librarian
    @PostMapping
    public ResponseEntity<LibrarianDTO> addLibrarian(@RequestBody LibrarianDTO librarianDTO) {
        LibrarianDTO createdLibrarian = librarianService.addLibrarian(librarianDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrarian);
    }

    //  Get all librarians
    @GetMapping
    public ResponseEntity<List<LibrarianDTO>> getAllLibrarians() {
        return ResponseEntity.ok(librarianService.getAllLibrarians());
    }

    //  Get librarian by ID
    @GetMapping("/{librarianId}")
    public ResponseEntity<LibrarianDTO> getLibrarianById(@PathVariable Long librarianId) {
        return ResponseEntity.ok(librarianService.getLibrarianById(librarianId));
    }

    //  Update librarian details
    @PutMapping("/{librarianId}")
    public ResponseEntity<LibrarianDTO> updateLibrarian(
            @PathVariable Long librarianId,
            @RequestBody LibrarianDTO updatedLibrarian) {
        return ResponseEntity.ok(librarianService.updateLibrarian(librarianId, updatedLibrarian));
    }

    // Delete librarian
    @DeleteMapping("/{librarianId}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable Long librarianId) {
        librarianService.deleteLibrarian(librarianId);
        return ResponseEntity.noContent().build();
    }

    //  Get all transactions handled by a librarian
    @GetMapping("/{librarianId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByLibrarian(@PathVariable Long librarianId) {
        return ResponseEntity.ok(librarianService.getTransactionsByLibrarian(librarianId));
    }
}
