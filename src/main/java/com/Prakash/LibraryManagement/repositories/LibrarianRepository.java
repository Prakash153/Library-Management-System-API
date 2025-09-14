package com.Prakash.LibraryManagement.repositories;

import com.Prakash.LibraryManagement.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
}
