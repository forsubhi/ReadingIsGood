package com.getir.service;


import com.getir.exception.NotEnoughBooksException;
import com.getir.model.Book;
import com.getir.model.Order;
import com.getir.model.OrderDetail;
import com.getir.repository.BookRepository;
import com.getir.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Order save(Order order) {
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(order);
            Book book = bookRepository.findById(orderDetail.getBookId()).get();
            Integer requiredCount = orderDetail.getCount();
            Integer stockCount = book.getStockCount();
            if (stockCount > requiredCount) {
                book.setStockCount(stockCount - requiredCount);
                bookRepository.save(book);
            } else {
                throw new NotEnoughBooksException("stock count= " + stockCount +
                        " required count=" + requiredCount
                );
            }

        }
        return orderRepository.save(order);

    }


    public List<Order> findAll() {
        return orderRepository.findAll();

    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();

    }

    public List<Order> filterByDate(Date startDate, Date endDate) {
        return orderRepository.getAllBetweenDates(startDate, endDate);

    }

    public Page<Order> findAll(Long customerId, Pageable pageable) {
        Page<Order> orderPage = orderRepository.findByCustomerId(customerId, pageable);
        return orderPage;
    }
}
