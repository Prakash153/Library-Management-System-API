package com.Prakash.LibraryManagement.repositories;

import com.Prakash.LibraryManagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
