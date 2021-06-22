package com.getir.service;


import com.getir.model.Book;
import com.getir.model.Statistics;
import com.getir.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    StatisticsRepository statisticsRepository;



    public List<Statistics> findAll() {
        return statisticsRepository.findAll();

    }

}
