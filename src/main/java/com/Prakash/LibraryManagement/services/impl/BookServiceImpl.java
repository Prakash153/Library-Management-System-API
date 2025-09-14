package com.Prakash.LibraryManagement.services.impl;

import com.Prakash.LibraryManagement.dtos.BookDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.entities.Book;
import com.Prakash.LibraryManagement.repositories.BookRepository;
import com.Prakash.LibraryManagement.repositories.TransactionRepository;
import com.Prakash.LibraryManagement.services.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return transactionRepository.findByBookId(bookId).stream()
                .map(tx -> modelMapper.map(tx, TransactionDTO.class))
                .toList();
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO updatedBook) {
        Book book = modelMapper.map(updatedBook,Book.class);
        book.setId(bookId);
        return modelMapper.map(bookRepository.save(book),BookDTO.class);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }


}
