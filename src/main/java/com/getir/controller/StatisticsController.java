package com.getir.controller;


import com.getir.model.Statistics;
import com.getir.repository.StatisticsRepository;
import com.getir.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/")
    ResponseEntity<List<Statistics>> findAll() {
        return ResponseEntity.ok(statisticsService.findAll());
    }


}
