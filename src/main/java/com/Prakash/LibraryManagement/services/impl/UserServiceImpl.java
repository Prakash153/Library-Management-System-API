package com.Prakash.LibraryManagement.services.impl;

import com.Prakash.LibraryManagement.dtos.TransactionDTO;
import com.Prakash.LibraryManagement.dtos.UserDTO;
import com.Prakash.LibraryManagement.entities.Transaction;
import com.Prakash.LibraryManagement.entities.UserEntity;
import com.Prakash.LibraryManagement.repositories.TransactionRepository;
import com.Prakash.LibraryManagement.repositories.UserRepository;
import com.Prakash.LibraryManagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;


    @Override
    public UserDTO registerNewUser(UserDTO inputUser) {
        UserEntity userEntity = modelMapper.map(inputUser, UserEntity.class);
        return modelMapper.map(userRepository.save(userEntity), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();


        return userEntities.stream()
                .map(userEntity ->
                        modelMapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
         UserEntity userEntity = userRepository.findById(id).orElse(null);
         return modelMapper.map(userEntity,UserDTO.class);
    }

    @Override
    public UserDTO updateUserDetails(long id, UserDTO updateUser) {
            if(userRepository.existsById(id)) {
                UserEntity updateUserEntity = modelMapper.map(updateUser, UserEntity.class);
                updateUserEntity.setId(id);

                return modelMapper.map(userRepository.save(updateUserEntity), UserDTO.class);
            }
            else
                return null ;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<TransactionDTO> getUserTransactions(long userId) {
         List<Transaction> transactions = transactionRepository.findByUserId(userId);
         return transactions.stream()
                 .map(transaction ->
                         modelMapper.map(transaction,TransactionDTO.class))
                 .collect(Collectors.toList());
    }
}
