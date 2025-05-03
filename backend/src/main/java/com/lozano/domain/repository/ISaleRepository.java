package com.lozano.domain.repository;

import com.lozano.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUserId(Long userId);
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
