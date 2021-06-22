package com.getir.repository;

import com.getir.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
     Page<Order> findByCustomerId(Long customerId, Pageable pageable);


     @Query(value = "select * from customer_order o where o.date BETWEEN :startDate AND :endDate",nativeQuery = true)
     public List<Order> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

}
