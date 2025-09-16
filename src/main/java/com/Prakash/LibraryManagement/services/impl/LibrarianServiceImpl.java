package com.Prakash.LibraryManagement.services.impl;

import com.Prakash.LibraryManagement.dtos.LibrarianDTO;
import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.entities.Librarian;
import com.Prakash.LibraryManagement.entities.Transaction;
import com.Prakash.LibraryManagement.exceptions.ResourceNotFoundException;
import com.Prakash.LibraryManagement.repositories.LibrarianRepository;
import com.Prakash.LibraryManagement.repositories.TransactionRepository;
import com.Prakash.LibraryManagement.services.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

    private final ModelMapper modelMapper;
    private final LibrarianRepository librarianRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public LibrarianDTO addLibrarian(LibrarianDTO librarianDTO) {
        Librarian librarian = modelMapper.map(librarianDTO, Librarian.class);
        Librarian saved = librarianRepository.save(librarian);
        return modelMapper.map(saved, LibrarianDTO.class);
    }

    @Override
    public List<LibrarianDTO> getAllLibrarians() {
        return librarianRepository.findAll()
                .stream()
                .map(librarian -> modelMapper.map(librarian, LibrarianDTO.class))
                .toList();
    }

    @Override
    public LibrarianDTO getLibrarianById(Long librarianId) {
        checkIfLibrarianExists(librarianId);
        Librarian librarian = librarianRepository.findById(librarianId).orElse(null);
        return (librarian != null) ? modelMapper.map(librarian, LibrarianDTO.class) : null;
    }

    private void checkIfLibrarianExists(Long librarianId) {
        boolean check = librarianRepository.existsById(librarianId);
        if(!check)
            throw new ResourceNotFoundException("Librarian not found with id: " + librarianId);

    }

    @Override
    public LibrarianDTO updateLibrarian(Long librarianId, LibrarianDTO updatedLibrarian) {
        checkIfLibrarianExists(librarianId);
        Librarian librarian =  modelMapper.map(updatedLibrarian,Librarian.class);
        return modelMapper.map(librarianRepository.save(librarian),LibrarianDTO.class);


    }

    @Override
    public void deleteLibrarian(Long librarianId) {
        checkIfLibrarianExists(librarianId);
        librarianRepository.deleteById(librarianId);
    }

    @Override
    public List<TransactionDTO> getTransactionsByLibrarian(Long librarianId) {
        checkIfLibrarianExists(librarianId);
        List<Transaction> transactions =  transactionRepository.findByLibrarianId(librarianId);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .collect(Collectors.toList());
    }
}

