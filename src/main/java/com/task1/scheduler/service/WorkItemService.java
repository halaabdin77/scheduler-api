package com.task1.scheduler.service;

import com.task1.scheduler.dto.CreateWorkItemRequest;
import com.task1.scheduler.dto.CreateWorkItemResponse;
import com.task1.scheduler.model.WorkItem;
import com.task1.scheduler.repository.WorkItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkItemService {

    private final WorkItemRepository repository;

    public WorkItemService(WorkItemRepository repository) {
        this.repository = repository;
    }

    public CreateWorkItemResponse scheduleWorkItem(CreateWorkItemRequest request) {

        if (request.endTime().isBefore(request.startTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        WorkItem entity = new WorkItem(
                null,
                request.title(),
                request.startTime(),
                request.endTime()
        );

        WorkItem saved = repository.save(entity);

        return new CreateWorkItemResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getStartTime(),
                saved.getEndTime()
        );
    }


    public List<CreateWorkItemResponse> getAllWorkItems() {

        return repository.findAll()
                .stream()
                .map(workItem -> new CreateWorkItemResponse(
                        workItem.getId(),
                        workItem.getTitle(),
                        workItem.getStartTime(),
                        workItem.getEndTime()
                ))
                .toList();
    }
    public List<CreateWorkItemResponse> findOverlappingWorkItems(LocalDateTime start, LocalDateTime end) {
        return repository
                .findByStartTimeLessThanAndEndTimeGreaterThan(end, start)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<CreateWorkItemResponse> findWorkItemsFullyInside(LocalDateTime start, LocalDateTime end) {
        return repository
                .findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(start, end)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private CreateWorkItemResponse toResponse(WorkItem w) {
        return new CreateWorkItemResponse(
                w.getId(),
                w.getTitle(),
                w.getStartTime(),
                w.getEndTime()
        );
    }
}
