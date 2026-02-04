package com.task1.scheduler.dto;

import java.time.LocalDateTime;

public record CreateWorkItemRequest(
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}


