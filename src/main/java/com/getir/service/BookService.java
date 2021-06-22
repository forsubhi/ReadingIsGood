package com.getir.service;


import com.getir.model.Book;
import com.getir.model.Customer;
import com.getir.repository.BookRepository;
import com.getir.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);

    }


    public List<Book> findAll() {
        return bookRepository.findAll();

    }

    public Book findById(Long id) {
        return bookRepository.findById(id).get();

    }

    public void deleteAll() {
        bookRepository.deleteAll();

    }
}
