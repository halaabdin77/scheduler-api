package com.task1.scheduler.service;

import com.task1.scheduler.item.EmailItem;
import com.task1.scheduler.item.ScheduledItem;
import com.task1.scheduler.item.StockAlertItem;
import com.task1.scheduler.notification.NotificationService;
import com.task1.scheduler.repository.ScheduledEmailRepository;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemScheduler {

    private final TrackedStockRepository stockRepository;
    private final ScheduledEmailRepository emailRepository;
    private final NotificationService notificationService;
    private final StockPriceService stockPriceService;

    public ItemScheduler(TrackedStockRepository stockRepository,
                         ScheduledEmailRepository emailRepository,
                         NotificationService notificationService,
                         StockPriceService stockPriceService) {

        this.stockRepository = stockRepository;
        this.emailRepository = emailRepository;
        this.notificationService = notificationService;
        this.stockPriceService = stockPriceService;
    }

    @Scheduled(fixedRate = 60000)
    public void processItems() {

        System.out.println("Running unified scheduler...");

        List<ScheduledItem> items = new ArrayList<>();

        emailRepository.findBySendAtBeforeAndSentFalse(LocalDateTime.now())
                .forEach(email ->
                        items.add(new EmailItem(
                                email.getRecipient(),
                                email.getSubject(),
                                email.getBody(),
                                notificationService
                        ))
                );

        stockRepository.findAll().forEach(stock ->
                items.add(new StockAlertItem(
                        stock,
                        stockPriceService,
                        notificationService
                ))
        );

        for (ScheduledItem item : items) {
            item.process();
        }
    }
}



