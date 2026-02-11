package com.task1.scheduler.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TrackedStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String symbol;
    private Double lastPrice;
    private String alertEmail;

}
