package com.getir.service;


import com.getir.model.Customer;
import com.getir.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);

    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

}
