package com.task1.scheduler.notification;

public interface NotificationService {
    void send(String to, String subject, String message);
}
