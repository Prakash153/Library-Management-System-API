package com.Prakash.LibraryManagement.services;

import com.Prakash.LibraryManagement.dtos.LibrarianDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;

import java.util.List;

public interface LibrarianService {
    LibrarianDTO addLibrarian(LibrarianDTO librarianDTO);

    List<LibrarianDTO> getAllLibrarians();

    LibrarianDTO getLibrarianById(Long librarianId);

    LibrarianDTO updateLibrarian(Long librarianId, LibrarianDTO updatedLibrarian);

    void deleteLibrarian(Long librarianId);

    List<TransactionDTO> getTransactionsByLibrarian(Long librarianId);
}
