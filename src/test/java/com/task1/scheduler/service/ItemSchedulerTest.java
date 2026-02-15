package com.task1.scheduler.service;

import com.task1.scheduler.config.TestNotificationConfig;
import com.task1.scheduler.model.TrackedStock;
import com.task1.scheduler.repository.TrackedStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemSchedulerTest {

    @Autowired
    private ItemScheduler scheduler;

    @Autowired
    private TrackedStockRepository repository;

    @BeforeEach
    void clearDatabase() {
        repository.deleteAll();
        TestNotificationConfig.sentSubjects.clear();
    }

    @Test
    void schedulerShouldSendEmailWhenPriceIncreases() {

        // Arrange
        TrackedStock stock = new TrackedStock();
        stock.setSymbol("AAPL");
        stock.setAlertEmail("test@mail.com");
        stock.setLastPrice(100.0); // lower than real price
        repository.save(stock);
        scheduler.processItems();




        assertFalse(TestNotificationConfig.sentSubjects.isEmpty(),
                "No email was actually triggered");

        assertTrue(TestNotificationConfig.sentSubjects.get(0)
                        .contains("Stock Up"),
                "Email subject does not contain the expected text");
    }
}
