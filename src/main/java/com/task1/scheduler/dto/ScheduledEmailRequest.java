package com.task1.scheduler.dto;
import java.time.LocalDateTime;

public record ScheduledEmailRequest(
        String recipent,
        String subject,
        String body,
        LocalDateTime sendAt
        ) {
}
