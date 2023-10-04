package com.example.bookmanagement.controller;

import com.example.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Define endpoints to handle HTTP requests for CRUD operations and book search
}
