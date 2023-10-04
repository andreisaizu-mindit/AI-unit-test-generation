package com.example.bookmanagement.repository;

import com.example.bookmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Add custom query methods if needed
}