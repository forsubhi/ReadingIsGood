package com.getir.controller;


import com.getir.model.Book;
import com.getir.model.Customer;
import com.getir.service.BookService;
import com.getir.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/")
   public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }


}
