package com.task1.scheduler.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "scheduled_emails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scheduledemail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipient;
    private String subject;
    private String body;

    private LocalDateTime sendAt;
    private boolean sent = false;
}

