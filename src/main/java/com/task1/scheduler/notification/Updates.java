package com.task1.scheduler.notification;
import org.springframework.stereotype.Service;

@Service
public class Updates {
    private final NotificationService emailservice;

    public Updates(NotificationService emailservice){
        this.emailservice = emailservice;
    }

}
