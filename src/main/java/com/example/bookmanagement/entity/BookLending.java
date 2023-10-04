package com.example.bookmanagement.entity;

import com.example.bookmanagement.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BookLending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BookstoreUser bookstoreUser;

    @ManyToOne
    private Book book;

    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDate lendingDate;

    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDate returnDate;

    private boolean returned;

}
