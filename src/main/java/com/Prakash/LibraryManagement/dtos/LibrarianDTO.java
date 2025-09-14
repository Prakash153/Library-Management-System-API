package com.Prakash.LibraryManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianDTO {
    private Long id;
    private String name;
    private String email;
    private String role; // LIBRARIAN, ADMIN
}