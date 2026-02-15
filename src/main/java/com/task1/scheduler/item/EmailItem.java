package com.task1.scheduler.item;

import com.task1.scheduler.notification.NotificationService;

public class EmailItem implements ScheduledItem {

    private final String recipient;
    private final String subject;
    private final String body;
    private final NotificationService notificationService;

    public EmailItem(String recipient,
                     String subject,
                     String body,
                     NotificationService notificationService) {

        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.notificationService = notificationService;
    }

    @Override
    public void process() {
        notificationService.send(recipient, subject, body);
    }

    @Override
    public boolean isProcessed() {
        return false;
    }

    @Override
    public void markProcessed() {

    }
}
