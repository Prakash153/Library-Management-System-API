package com.Prakash.LibraryManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String membershipType; // STUDENT, FACULTY, MEMBER
    private String status;         // ACTIVE, INACTIVE
    private LocalDate createdAt;
}