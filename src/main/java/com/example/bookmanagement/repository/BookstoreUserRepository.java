package com.example.bookmanagement.repository;

import com.example.bookmanagement.entity.BookstoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreUserRepository extends JpaRepository<BookstoreUser, Long> {
    BookstoreUser findByUsername(String username);
    BookstoreUser save(BookstoreUser user);
}
