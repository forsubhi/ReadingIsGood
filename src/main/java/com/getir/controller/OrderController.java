package com.getir.controller;


import com.getir.model.Order;
import com.getir.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }


    @GetMapping("/filter-by-id")
    public ResponseEntity<Order> getById(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/filter-by-date")
    ResponseEntity<List<Order>> filterByDate(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(orderService.filterByDate(startDate, endDate));
    }


}
