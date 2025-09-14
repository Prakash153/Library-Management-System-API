package com.Prakash.LibraryManagement.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Long userId;
    private Long bookId;
    private Long librarianId;

    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private String status;  // ISSUED, RETURNED, OVERDUE

    private Double fine;
}
