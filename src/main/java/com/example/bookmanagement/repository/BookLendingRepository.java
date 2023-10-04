package com.example.bookmanagement.repository;

import com.example.bookmanagement.entity.BookLending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLendingRepository extends JpaRepository<BookLending, Long>, JpaSpecificationExecutor<BookLending> {
    List<BookLending> findByReturnDateBeforeAndReturnedFalse(LocalDate currentDate);
    // Add other custom query methods if needed
}
