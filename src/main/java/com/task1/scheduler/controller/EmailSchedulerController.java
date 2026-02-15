package com.task1.scheduler.controller;

import com.task1.scheduler.dto.ScheduledEmailRequest;
import com.task1.scheduler.model.ScheduledEmail;
import com.task1.scheduler.repository.ScheduledEmailRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailSchedulerController {

    private final ScheduledEmailRepository repository;

    public EmailSchedulerController(ScheduledEmailRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/schedule")
    public String scheduleEmail(@RequestBody ScheduledEmailRequest request) {
        ScheduledEmail email = new ScheduledEmail(
                null,
                request.recipent(),
                request.subject(),
                request.body(),
                request.sendAt(),
                false
        );

        repository.save(email);
        return "Email scheduled successfully!";
    }
}
