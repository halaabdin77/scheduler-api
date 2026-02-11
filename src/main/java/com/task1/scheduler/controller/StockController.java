package com.task1.scheduler.controller;

import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final TrackedStockRepository repository;

    public StockController(TrackedStockRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/track")
    public String trackStock(@RequestBody TrackedStock stock) {
        repository.save(stock);
        return "Stock tracking started!";
    }
}
