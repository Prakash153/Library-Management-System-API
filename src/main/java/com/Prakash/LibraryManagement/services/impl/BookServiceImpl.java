package com.Prakash.LibraryManagement.services.impl;

import com.Prakash.LibraryManagement.dtos.BookDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.entities.Book;
import com.Prakash.LibraryManagement.entities.Transaction;
import com.Prakash.LibraryManagement.exceptions.ResourceNotFoundException;
import com.Prakash.LibraryManagement.repositories.BookRepository;
import com.Prakash.LibraryManagement.repositories.TransactionRepository;
import com.Prakash.LibraryManagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public BookDTO addBook(BookDTO newBook) {
        // For now, just return the DTO or map it to entity and save
        Book book = modelMapper.map(newBook, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        checkIfExists(bookId);
        return bookRepository.findById(bookId)
                .map(book -> modelMapper.map(book, BookDTO.class))
                .orElse(null);
    }



    @Override
    public List<BookDTO> searchBooks(String title, String author, String genre, String isbn) {
        return bookRepository.findByFilters(title, author, genre, isbn).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
    }

    @Override
    public List<TransactionDTO> getBookTransactions(Long bookId) {
        checkIfExists(bookId);
        List<Transaction> transactions = transactionRepository.findByBookId(bookId);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction,TransactionDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO updatedBook) {
         checkIfExists(bookId);
        Book book = modelMapper.map(updatedBook, Book.class);
        book.setId(bookId);
        return modelMapper.map(bookRepository.save(book), BookDTO.class);
    }

    @Override
    public void deleteBook(Long bookId) {
        checkIfExists(bookId);
        bookRepository.deleteById(bookId);
    }
    private void checkIfExists(Long bookId) {
        boolean check = bookRepository.existsById(bookId);
        if (!check)
            throw new ResourceNotFoundException("Book not found with id : " + bookId);

    }

}
