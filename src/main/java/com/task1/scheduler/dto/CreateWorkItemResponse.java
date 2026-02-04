package com.task1.scheduler.dto;

import java.time.LocalDateTime;

public record CreateWorkItemResponse(
        Long id,
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}

