package com.lozano.application.service.impl;

import com.lozano.application.dto.Sale.SaleRequestDTO;
import com.lozano.application.dto.Sale.SaleResponseDTO;
import com.lozano.application.mapper.SaleMapper;
import com.lozano.application.service.ISaleService;
import com.lozano.domain.entity.Sale;
import com.lozano.domain.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ISaleService {

    private final ISaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public List<SaleResponseDTO> findAll() {
        List<Sale> sales = saleRepository.findAll();
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public SaleResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de venta no válido");
        }
        return saleRepository.findById(id)
                .map(saleMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public SaleResponseDTO create(SaleRequestDTO dto) {
        Sale sale = saleMapper.toEntity(dto);
        sale.calculateTotals();
        return saleMapper.toResponseDTO(saleRepository.save(sale));
    }

    @Override
    public SaleResponseDTO update(Long id, SaleRequestDTO dto) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        saleMapper.updateEntityFromDTO(dto, existingSale);
        return saleMapper.toResponseDTO(saleRepository.save(existingSale));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de venta no válido");
        }
        if (!saleRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada");
        }
        saleRepository.deleteById(id);
    }

    @Override
    public List<SaleResponseDTO> findByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("ID de usuario no válido");
        }
        List<Sale> sales = saleRepository.findByUserId(userId);
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas para el usuario");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<SaleResponseDTO> findBySaleDateBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Fechas no válidas");
        }
        List<Sale> sales = saleRepository.findBySaleDateBetween(start, end);
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas en el rango de fechas");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<SaleResponseDTO> findDiscountedSales() {
        List<Sale> sales = saleRepository.findDiscountedSales();
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas con descuento");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<SaleResponseDTO> findByCouponCode(String couponCode) {
        if (!StringUtils.hasText(couponCode)) {
            throw new IllegalArgumentException("Código de cupón no válido");
        }
        List<Sale> sales = saleRepository.findByCouponCode(couponCode);
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas con el cupón especificado");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<SaleResponseDTO> findSalesAboveAmount(Double minAmount) {
        if (minAmount == null || minAmount <= 0) {
            throw new IllegalArgumentException("Monto mínimo no válido");
        }
        List<Sale> sales = saleRepository.findSalesAboveAmount(minAmount);
        if (sales.isEmpty()) {
            throw new RuntimeException("No se encontraron ventas sobre el monto especificado");
        }
        return sales.stream()
                .map(saleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public boolean existsSaleForUser(Long saleId, Long userId) {
        if (saleId == null || saleId <= 0 || userId == null || userId <= 0) {
            throw new IllegalArgumentException("IDs no válidos");
        }
        return saleRepository.existsByIdAndUserId(saleId, userId);
    }

    private void validateSaleRequestDTO(SaleRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de venta no puede ser nulo");
        }
        if (dto.getUserId() == null || dto.getUserId() <= 0) {
            throw new IllegalArgumentException("Usuario no válido");
        }
    }
}
