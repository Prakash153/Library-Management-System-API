package com.Prakash.LibraryManagement.services.impl;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.entities.Transaction;
import com.Prakash.LibraryManagement.entities.enums.TransactionStatus;
import com.Prakash.LibraryManagement.exceptions.ResourceNotFoundException;
import com.Prakash.LibraryManagement.repositories.BookRepository;
import com.Prakash.LibraryManagement.repositories.LibrarianRepository;
import com.Prakash.LibraryManagement.repositories.TransactionRepository;
import com.Prakash.LibraryManagement.repositories.UserRepository;
import com.Prakash.LibraryManagement.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LibrarianRepository librarianRepository;
    private final ModelMapper modelMapper;

    @Override
    public TransactionDTO issueBook(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setUser(userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + transactionDTO.getId())));

        transaction.setBook(bookRepository.findById(transactionDTO.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + transactionDTO.getBookId())));

        transaction.setLibrarian(librarianRepository.findById(transactionDTO.getLibrarianId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + transactionDTO.getLibrarianId())));

        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(14));
        transaction.setStatus(TransactionStatus.ISSUED);
        transaction.setFine(0.0);

        Transaction saved = transactionRepository.save(transaction);
        return toDTO(saved);
    }

    @Override
    public TransactionDTO returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if (transaction != null && transaction.getStatus() == TransactionStatus.ISSUED) {
            transaction.setReturnDate(LocalDate.now());

            if (transaction.getDueDate().isBefore(LocalDate.now())) {
                long daysLate = java.time.temporal.ChronoUnit.DAYS.between(
                        transaction.getDueDate(),
                        LocalDate.now()
                );
                transaction.setFine(daysLate * 10.0);
            }

            transaction.setStatus(TransactionStatus.RETURNED);
            Transaction saved = transactionRepository.save(transaction);
            return toDTO(saved);
        }
        return null;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public List<TransactionDTO> getOverdueTransactions() {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> tx.getStatus() == TransactionStatus.ISSUED &&
                        tx.getDueDate().isBefore(LocalDate.now()))
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<TransactionDTO> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<TransactionDTO> getTransactionsByBook(Long bookId) {
        return transactionRepository.findByBookId(bookId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<TransactionDTO> getTransactionsByLibrarian(Long librarianId) {
        return transactionRepository.findByLibrarianId(librarianId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = modelMapper.map(transaction, TransactionDTO.class);

        // Manually map IDs (since ModelMapper won't automatically map nested entity IDs)
        dto.setUserId(transaction.getUser().getId());
        dto.setBookId(transaction.getBook().getId());
        dto.setLibrarianId(transaction.getLibrarian().getId());
        dto.setStatus(transaction.getStatus().name());

        return dto;
    }
}
