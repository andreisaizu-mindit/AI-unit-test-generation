package com.example.bookmanagement.controller;

import com.example.bookmanagement.entity.BookLending;
import com.example.bookmanagement.service.BookLendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-lendings")
@RequiredArgsConstructor
public class BookLendingController {

    private final BookLendingService bookLendingService;

    @PostMapping
    public ResponseEntity<BookLending> createBookLending(@RequestBody BookLending bookLending) {
        BookLending createdBookLending = bookLendingService.createBookLending(bookLending);
        return new ResponseEntity<>(createdBookLending, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookLending>> searchBookLendings(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "bookId", required = false) Long bookId,
            @RequestParam(value = "userEmail", required = false) String userEmail
    ) {
        List<BookLending> bookLendings = bookLendingService.searchLendings(userId, bookId, userEmail);
        return new ResponseEntity<>(bookLendings, HttpStatus.OK);
    }
}