package com.lozano.application.service;

import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;

import java.util.List;

public interface ISaleDetailService {
    SaleDetailResponseDTO findById(Long id);
    List<SaleDetailResponseDTO> findBySaleId(Long saleId);
    List<SaleDetailResponseDTO> findByProductId(Long productId);
    SaleDetailResponseDTO create(SaleDetailRequestDTO dto);
    SaleDetailResponseDTO update(Long id, SaleDetailRequestDTO dto);
    void deleteById(Long id);
    boolean existsByIdAndSaleId(Long detailId, Long saleId);
}
