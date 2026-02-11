package com.task1.scheduler.service;

import com.task1.scheduler.config.TestNotificationConfig;
import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockMonitorSchedulerTest {

    @Autowired
    private StockMonitorScheduler scheduler;

    @Autowired
    private TrackedStockRepository repository;

    @Test
    void schedulerShouldSendEmailWhenPriceIncreases() {

        TestNotificationConfig.sentSubjects.clear();

        TrackedStock stock = new TrackedStock();
        stock.setSymbol("AAPL");
        stock.setAlertEmail("test@mail.com");
        stock.setLastPrice(100.0);
        repository.save(stock);

        scheduler.monitorStock();


        assertFalse(TestNotificationConfig.sentSubjects.isEmpty(),
                "No email was triggered");

        assertTrue(TestNotificationConfig.sentSubjects.get(0).contains("Stock Up"));
    }
}
