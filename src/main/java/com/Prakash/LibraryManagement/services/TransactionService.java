package com.Prakash.LibraryManagement.services;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO issueBook(TransactionDTO transactionDTO);

    TransactionDTO returnBook(Long transactionId);

    List<TransactionDTO> getAllTransactions();

    TransactionDTO getTransactionById(Long id);

    List<TransactionDTO> getOverdueTransactions();

    List<TransactionDTO> getTransactionsByUser(Long userId);

    List<TransactionDTO> getTransactionsByBook(Long bookId);

    List<TransactionDTO> getTransactionsByLibrarian(Long librarianId);
}
