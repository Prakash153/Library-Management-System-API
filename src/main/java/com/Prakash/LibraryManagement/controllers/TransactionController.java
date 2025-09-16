package com.Prakash.LibraryManagement.controllers;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<TransactionDTO> issueBook(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.issueBook(transactionDTO));
    }

    @PostMapping("/return/{transactionId}")
    public ResponseEntity<TransactionDTO> returnBook(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.returnBook(transactionId));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<TransactionDTO>> getOverdueTransactions() {
        return ResponseEntity.ok(transactionService.getOverdueTransactions());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUser(userId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(transactionService.getTransactionsByBook(bookId));
    }

    @GetMapping("/librarian/{librarianId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByLibrarian(@PathVariable Long librarianId) {
        return ResponseEntity.ok(transactionService.getTransactionsByLibrarian(librarianId));
    }
}
