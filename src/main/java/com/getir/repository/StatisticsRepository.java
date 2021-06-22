package com.getir.repository;

import com.getir.model.Book;
import com.getir.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Void> {
}
