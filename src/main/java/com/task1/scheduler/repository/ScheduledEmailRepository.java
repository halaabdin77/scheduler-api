package com.task1.scheduler.repository;

import com.task1.scheduler.model.ScheduledEmail;
import com.task1.scheduler.model.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledEmailRepository
        extends JpaRepository<ScheduledEmail, Long> {

    List<ScheduledEmail> findBySendAtBeforeAndSentFalse(LocalDateTime time);
}

