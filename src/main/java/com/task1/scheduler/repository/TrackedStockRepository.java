package com.task1.scheduler.repository;
import com.task1.scheduler.model.TrackedStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackedStockRepository extends JpaRepository <TrackedStock, Long>{
}
