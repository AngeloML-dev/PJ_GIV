package com.lozano.application.service;

import com.lozano.application.dto.Sale.SaleRequestDTO;
import com.lozano.application.dto.Sale.SaleResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ISaleService {
    // Métodos existentes
    SaleResponseDTO create(SaleRequestDTO dto);
    SaleResponseDTO update(Long id, SaleRequestDTO dto);
    SaleResponseDTO findById(Long id);
    List<SaleResponseDTO> findAll();
    void deleteById(Long id);
    List<SaleResponseDTO> findByUserId(Long userId);
    List<SaleResponseDTO> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);
    List<SaleResponseDTO> findDiscountedSales();

    // Añadir estos métodos que faltan en la interfaz
    List<SaleResponseDTO> findByCouponCode(String couponCode);
    List<SaleResponseDTO> findSalesAboveAmount(Double minAmount);
    boolean existsSaleForUser(Long saleId, Long userId);
}
