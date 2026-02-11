package com.task1.scheduler.config;

import com.task1.scheduler.notification.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class TestNotificationConfig {

    public static List<String> sentSubjects = new ArrayList<>();
    @Bean
    @Primary
    public NotificationService testNotificationService() {
        return (to, subject, message) ->
                System.out.println("test email: " + subject);
    }
}
