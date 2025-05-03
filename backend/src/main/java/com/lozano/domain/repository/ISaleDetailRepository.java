package com.lozano.domain.repository;

import com.lozano.domain.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findBySaleId(Long saleId);
}
