package com.Prakash.LibraryManagement.entities;

import com.Prakash.LibraryManagement.entities.enums.MembershipType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType; // STUDENT, FACULTY, MEMBER

    private String status; // ACTIVE, INACTIVE

    private LocalDate createdAt = LocalDate.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}