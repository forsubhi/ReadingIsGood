package com.getir.repository;

import com.getir.model.Book;
import com.getir.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
