package com.example.bookmanagement.service;

import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Implement CRUD operations (save, update, delete)

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchBooksByCreator(String createdBy) {
        return bookRepository.findByCreatedBy(createdBy);
    }

    // Add other book search methods

    // Add book lending related methods here
}