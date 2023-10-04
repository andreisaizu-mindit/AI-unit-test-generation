package com.example.bookmanagement.service;

import com.example.bookmanagement.entity.BookLending;
import com.example.bookmanagement.repository.BookLendingRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookLendingService {
    @Autowired
    private BookLendingRepository bookLendingRepository;

    // Other methods for book lending management

    public List<BookLending> getOverdueLendings() {
        return bookLendingRepository.findByReturnDateBeforeAndReturnedFalse(LocalDate.now());
    }

    public BookLending createBookLending(BookLending bookLending) {
        // Implement the logic to create a new book lending record
        // You might want to set the lending date, return date, etc. based on your requirements
        return bookLendingRepository.save(bookLending);
    }

    public List<BookLending> searchLendings(Long userId, Long bookId, String userEmail) {
        return bookLendingRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(cb.equal(root.get("bookstoreUser").get("id"), userId));
            }

            if (bookId != null) {
                predicates.add(cb.equal(root.get("book").get("id"), bookId));
            }

            if (userEmail != null && !userEmail.isEmpty()) {
                predicates.add(cb.equal(root.get("bookstoreUser").get("email"), userEmail));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
