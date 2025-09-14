package com.Prakash.LibraryManagement.services;

import com.Prakash.LibraryManagement.dtos.BookDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long bookId);

    List<BookDTO> searchBooks(String title, String author, String genre, String isbn);

    List<TransactionDTO> getBookTransactions(Long bookId);

    BookDTO updateBook(Long bookId, BookDTO updatedBook);

    void deleteBook(Long bookId);

    BookDTO addBook(BookDTO newBook);
}
