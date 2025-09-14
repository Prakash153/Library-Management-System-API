package com.Prakash.LibraryManagement.repositories;

import com.Prakash.LibraryManagement.entities.Librarian;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
}
