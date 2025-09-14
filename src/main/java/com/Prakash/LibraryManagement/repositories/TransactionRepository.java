package com.Prakash.LibraryManagement.repositories;

import com.Prakash.LibraryManagement.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
