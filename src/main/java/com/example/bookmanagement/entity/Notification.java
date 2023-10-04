package com.example.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;
    private LocalDateTime timestamp;

    @ManyToOne
    private BookstoreUser bookstoreUser;

    // Add any other attributes needed for notifications

    // Constructor, getters, setters, and other methods
}

