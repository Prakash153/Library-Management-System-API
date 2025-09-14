package com.Prakash.LibraryManagement.entities;


import com.Prakash.LibraryManagement.entities.enums.LibrarianRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "librarians")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private LibrarianRole role; // LIBRARIAN, ADMIN

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    private List<Transaction> transactions ;
}