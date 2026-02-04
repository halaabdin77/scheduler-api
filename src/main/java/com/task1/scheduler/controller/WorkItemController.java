package com.task1.scheduler.controller;

import com.task1.scheduler.dto.CreateWorkItemRequest;
import com.task1.scheduler.dto.CreateWorkItemResponse;
import com.task1.scheduler.service.WorkItemService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/work-items")
public class WorkItemController {

    private final WorkItemService service;

    public WorkItemController(WorkItemService service) {
        this.service = service;
    }

    @PostMapping
    public CreateWorkItemResponse createWorkItem(@RequestBody CreateWorkItemRequest request) {
        return service.scheduleWorkItem(request);
    }

    @GetMapping
    public List<CreateWorkItemResponse> getOverlappingWorkItems(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return service.findOverlappingWorkItems(start, end);
    }

    @GetMapping("/inside")
    public List<CreateWorkItemResponse> getWorkItemsFullyInside(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return service.findWorkItemsFullyInside(start, end);
    }
}
