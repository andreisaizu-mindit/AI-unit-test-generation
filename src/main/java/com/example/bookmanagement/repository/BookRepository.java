package com.example.bookmanagement.repository;

import com.example.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCreatedBy(String createdBy);
    // Add other custom search methods if needed
}