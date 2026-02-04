package com.task1.scheduler.repository;

import com.task1.scheduler.model.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

    List<WorkItem> findByStartTimeLessThanAndEndTimeGreaterThan(
            LocalDateTime end, LocalDateTime start);

    List<WorkItem> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(
            LocalDateTime start, LocalDateTime end);
}
