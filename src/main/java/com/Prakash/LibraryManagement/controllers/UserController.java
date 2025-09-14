package com.Prakash.LibraryManagement.controllers;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.dtos.UserDTO;
import com.Prakash.LibraryManagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO inputUser) {
        UserDTO userDTO = userService.registerNewUser(inputUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable(name = "userId") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUserDetails(@PathVariable(name = "userId") long id,
                                              @RequestBody UserDTO updateUser) {
        UserDTO updatedUserDTO = userService.updateUserDetails(id, updateUser);

        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") long id ){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/transactions")
    ResponseEntity<List<TransactionDTO>> getUserTransactions(@PathVariable long userId){
      return ResponseEntity.ok(userService.getUserTransactions(userId));
    }
}
