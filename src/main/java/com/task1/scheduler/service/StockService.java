package com.task1.scheduler.service;

import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final TrackedStockRepository repository;

    public StockService(TrackedStockRepository repository) {
        this.repository = repository;
    }

    public TrackedStock save(TrackedStock stock) {
        return repository.save(stock);
    }

    public List<TrackedStock> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public void updateAllPrices(StockPriceService priceService) {

        List<TrackedStock> stocks = repository.findAll();

        for (TrackedStock stock : stocks) {

            Double currentPrice = priceService.getCurrentPrice(stock.getSymbol());

            if (currentPrice != null) {
                stock.setLastPrice(currentPrice);
                repository.save(stock);
            }
        }
    }

}
