package com.getir.controller;


import com.getir.configuration.ApiPageable;
import com.getir.model.Customer;
import com.getir.model.Order;
import com.getir.service.CustomerService;
import com.getir.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/find-orders")
    @ApiPageable
    ResponseEntity<Page<Order>> findOrdersOfCustomer(Pageable pageable,
                                                     @RequestParam Long customerId )
    {
        return ResponseEntity.ok(orderService.findAll(customerId,pageable));

    }


}
