package com.task1.scheduler.repository;

import com.task1.scheduler.model.Scheduledemail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledEmailRepository extends JpaRepository<Scheduledemail, Long> {

    List<Scheduledemail> findBySendAtBeforeAndSentFalse(LocalDateTime now);
}

