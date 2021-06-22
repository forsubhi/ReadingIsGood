package com.getir;

import com.getir.controller.BookController;
import com.getir.controller.CustomerController;
import com.getir.controller.OrderController;
import com.getir.exception.NotEnoughBooksException;
import com.getir.model.*;
import com.getir.service.BookService;
import com.getir.service.CustomerService;
import com.getir.service.OrderService;
import com.getir.service.StatisticsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ReadingIsGoodApplicationTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    BookService bookService;

    @Autowired
    BookController bookController;

    @Autowired
    OrderService orderService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    CustomerController customerController;

    @Autowired
    OrderController orderController;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddCustomer() {
        getCustomer();
        ResponseEntity<List<Customer>> responseEntity = customerController.findAll();
        List<Customer> customers = responseEntity.getBody();
        assert (customers.size() == 1);
    }


    @Test
    void testAddBooks() {
        Book book = getBook("Java");
        bookController.save(book);
        book = getBook("C#");
        bookController.save(book);
        book = getBook("GO");
        bookController.save(book);
        ResponseEntity<List<Book>> responseEntity = bookController.findAll();
        List<Book> body = responseEntity.getBody();
        assert (body.size() == 3);
    }


    @Test
    void testAddOrder() {
        Customer savedCustomer = getCustomer();
        Order order = new Order();
        order.setCustomerId(savedCustomer.getId());
        order.setDate(new Date());
        Set<OrderDetail> orderDetails = new HashSet();
        OrderDetail orderDetail = new OrderDetail();
        Book book = getBook("Java");
        book = bookService.save(book);
        orderDetail.setBookId(book.getId());
        orderDetail.setCount(20);
        orderDetails.add(orderDetail);
        order.setOrderDetails(orderDetails);
        orderController.save(order);
        book = bookService.findById(book.getId());
        assert (book.getStockCount() == 80);
        ResponseEntity<List<Order>> responseEntity = orderController.findAll();
        List<Order> orders = responseEntity.getBody();
        assert (orders.size() == 1);
        ResponseEntity<Order> orderResponseEntity = orderController.getById(order.getId());
        Order responseEntityBody = orderResponseEntity.getBody();
        assert (responseEntityBody.getId().equals(order.getId()));
    }


    @Test
    void testNotEnoughBooksException() {
        assertThrows(NotEnoughBooksException.class, () -> {
            Customer savedCustomer = getCustomer();
            Order order = new Order();
            order.setCustomerId(savedCustomer.getId());
            order.setDate(new Date());
            Set<OrderDetail> orderDetails = new HashSet();
            OrderDetail orderDetail = new OrderDetail();
            Book book = getBook("Java");
            book = bookService.save(book);
            orderDetail.setBookId(book.getId());
            orderDetail.setCount(120);
            orderDetails.add(orderDetail);
            order.setOrderDetails(orderDetails);
            orderService.save(order);
        });
    }


    @Test
    void testStatistics() {
        Customer savedCustomer = getCustomer();
        Order order = new Order();
        order.setCustomerId(savedCustomer.getId());
        order.setDate(new Date());
        Set<OrderDetail> orderDetails = new HashSet();
        OrderDetail orderDetail = new OrderDetail();
        Book book = getBook("Java");
        book = bookService.save(book);
        orderDetail.setBookId(book.getId());
        orderDetail.setCount(20);
        orderDetails.add(orderDetail);

        book = getBook("C#");
        book = bookService.save(book);
        orderDetail = new OrderDetail();
        orderDetail.setBookId(book.getId());
        orderDetail.setCount(30);
        orderDetails.add(orderDetail);

        book = getBook("GO");
        book = bookService.save(book);
        orderDetail = new OrderDetail();
        orderDetail.setBookId(book.getId());
        orderDetail.setCount(40);
        orderDetails.add(orderDetail);

        order.setOrderDetails(orderDetails);
        orderService.save(order);
        List<Statistics> statistics = statisticsService.findAll();
        Statistics statistic = statistics.get(0);
        assert (statistic.getTotalBookCount() == 90);
        assert (statistic.getTotalOrderCount() == 1);
        assert (statistic.getTotalPurchasedAmount() == 9000);
    }

    private void clearData() {
        orderService.deleteAll();
        customerService.deleteAll();
        bookService.deleteAll();
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setEmail("forsubhit@gmail.com");
        customer.setFirstname("Muhammed Suphi");
        customer.setLastname("Şeyhkuruş");
        customer.setPhone("05380603164");
        ResponseEntity<Customer> responseEntity = customerController.save(customer);
        Customer savedCustomer = responseEntity.getBody();
        return savedCustomer;
    }

    private Book getBook(String name) {
        Book book = new Book();
        book.setStockCount(100);
        book.setName(name);
        book.setPrice(100);
        return book;
    }

    @AfterEach
    private void clearDataAfterTest() {
        clearData();
    }


}
