package com.task1.scheduler.item;

import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.notification.NotificationService;
import com.task1.scheduler.service.StockPriceService;

public class StockAlertItem implements ScheduledItem {

    private final TrackedStock stock;
    private final StockPriceService stockPriceService;
    private final NotificationService notificationService;

    public StockAlertItem(TrackedStock stock,
                          StockPriceService stockPriceService,
                          NotificationService notificationService) {

        this.stock = stock;
        this.stockPriceService = stockPriceService;
        this.notificationService = notificationService;
    }

    @Override
    public void process() {

        Double currentPrice = stockPriceService.getCurrentPrice(stock.getSymbol());
        Double lastPrice = stock.getLastPrice();

        if (currentPrice == null) return;

        if (lastPrice != null && currentPrice > lastPrice) {
            notificationService.send(
                    stock.getAlertEmail(),
                    "Stock Up: " + stock.getSymbol(),
                    "Price increased from " + lastPrice + " to " + currentPrice
            );
        }

        stock.setLastPrice(currentPrice);
    }

    @Override
    public boolean isProcessed() {
        return false;
    }

    @Override
    public void markProcessed() {

    }
}

