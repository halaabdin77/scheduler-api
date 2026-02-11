package com.task1.scheduler.service;

import com.task1.scheduler.model.Scheduledemail;
import com.task1.scheduler.notification.NotificationService;
import com.task1.scheduler.repository.ScheduledEmailRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailSendingScheduler {

    private final ScheduledEmailRepository repository;
    private final NotificationService notifservice;

    public EmailSendingScheduler(ScheduledEmailRepository repository,
                                 NotificationService notifservice) {
        this.repository = repository;
        this.notifservice = notifservice;
    }

    @Scheduled(fixedRate = 30000)
    public void sendScheduledEmails() {

        List<Scheduledemail> emails =
                repository.findBySendAtBeforeAndSentFalse(LocalDateTime.now());

        for (Scheduledemail email : emails) {
            notifservice.send(
                    email.getRecipient(),
                    email.getSubject(),
                    email.getBody()
            );

            email.setSent(true);
            repository.save(email);
        }
    }
}
