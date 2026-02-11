package com.task1.scheduler.service;

import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.notification.NotificationService;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockMonitorScheduler {

    private final TrackedStockRepository repository;
    private final StockPriceService stockPriceService;
    private final NotificationService notificationService;

    public StockMonitorScheduler(TrackedStockRepository repository,
                                 StockPriceService stockPriceService,
                                 NotificationService notificationService) {
        this.repository = repository;
        this.stockPriceService = stockPriceService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 300000)
    public void monitorStock() {

        System.out.println("Checking tracked stock...");

        Optional<TrackedStock> optionalStock = repository.findAll().stream().findFirst();

        if (optionalStock.isEmpty()) {
            System.out.println("No stock found in database.");
            return;
        }

        TrackedStock stock = optionalStock.get();

        Double currentPrice = stockPriceService.getCurrentPrice(stock.getSymbol());
        Double lastPrice = stock.getLastPrice();

        System.out.println("Stock: " + stock.getSymbol());
        System.out.println("Current price: " + currentPrice);
        System.out.println("Last price: " + lastPrice);

        if (currentPrice == null) {
            System.out.println("Price unavailable");
            return;
        }

        // First run → just store price
//        if (lastPrice == null) {
//            stock.setLastPrice(currentPrice);
//            repository.save(stock);
//            return;
//        }
        if (currentPrice > lastPrice) {
            notificationService.send(
                    stock.getAlertEmail(),
                    "Stock Up: " + stock.getSymbol(),
                    "Price increased from " + lastPrice + " to " + currentPrice
            );
        }

        else if (currentPrice < lastPrice) {
            notificationService.send(
                    stock.getAlertEmail(),
                    "Stock Down: " + stock.getSymbol(),
                    "Price dropped from " + lastPrice + " to " + currentPrice
            );
        }

        stock.setLastPrice(currentPrice);
        repository.save(stock);
    }
}
