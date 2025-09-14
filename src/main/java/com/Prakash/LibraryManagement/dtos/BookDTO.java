package com.Prakash.LibraryManagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int publicationYear;
    private int copiesAvailable;
    private int totalCopies;
}