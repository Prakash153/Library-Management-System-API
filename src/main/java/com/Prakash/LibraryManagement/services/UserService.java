package com.Prakash.LibraryManagement.services;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.dtos.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO registerNewUser(UserDTO inputUser);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUserDetails(long id, UserDTO updateUser);

    void deleteUserById(long id);

    List<TransactionDTO> getUserTransactions(long userId);
}
