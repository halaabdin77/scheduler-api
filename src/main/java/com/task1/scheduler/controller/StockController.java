package com.task1.scheduler.controller;

import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.service.StockService;
import com.task1.scheduler.service.StockPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService service;
    private final StockPriceService priceService;

    public StockController(StockService service,
                           StockPriceService priceService) {
        this.service = service;
        this.priceService = priceService;
    }

    @PostMapping("/update-prices")
    public String updatePrices() {
        service.updateAllPrices(priceService);
        return "Prices updated!";
    }

    @GetMapping
    public List<TrackedStock> getAllTrackedStocks() {
        return service.getAll();
    }

    @PostMapping("/track")
    public TrackedStock trackStock(@RequestBody TrackedStock stock) {
        return service.save(stock);
    }

    @DeleteMapping("/{id}")
    public String deleteStock(@PathVariable Long id) {
        service.delete(id);
        return "Stock deleted!";
    }
}

