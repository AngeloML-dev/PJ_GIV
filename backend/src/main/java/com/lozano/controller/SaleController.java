package com.lozano.controller;

import com.lozano.application.dto.Sale.SaleRequestDTO;
import com.lozano.application.dto.Sale.SaleResponseDTO;
import com.lozano.application.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {
    private final ISaleService saleService;

    @GetMapping
    public List<SaleResponseDTO> listar() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public SaleResponseDTO getSaleById(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SaleResponseDTO> getSalesByUserId(@PathVariable Long userId) {
        return saleService.findByUserId(userId);
    }

    @GetMapping("/date-range")
    public List<SaleResponseDTO> getSalesByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return saleService.findBySaleDateBetween(start, end);
    }

    @GetMapping("/discounted")
    public List<SaleResponseDTO> getDiscountedSales() {
        return saleService.findDiscountedSales();
    }

    @GetMapping("/coupon/{couponCode}")
    public List<SaleResponseDTO> getSalesByCouponCode(@PathVariable String couponCode) {
        return saleService.findByCouponCode(couponCode);
    }

    @GetMapping("/amount-above/{minAmount}")
    public List<SaleResponseDTO> getSalesAboveAmount(@PathVariable Double minAmount) {
        return saleService.findSalesAboveAmount(minAmount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequestDTO requestDTO) {
        SaleResponseDTO updatedSale = saleService.update(id, requestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleRequestDTO requestDTO) {
        SaleResponseDTO createdSale = saleService.create(requestDTO);
        return ResponseEntity.status(201).body(createdSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleById(@PathVariable Long id) {
        saleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/{saleId}/{userId}")
    public boolean existsSaleForUser(@PathVariable Long saleId, @PathVariable Long userId) {
        return saleService.existsSaleForUser(saleId, userId);
    }
}
