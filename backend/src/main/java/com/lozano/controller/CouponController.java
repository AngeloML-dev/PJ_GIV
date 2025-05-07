package com.lozano.controller;

import com.lozano.application.dto.Coupon.CouponRequestDTO;
import com.lozano.application.dto.Coupon.CouponResponseDTO;
import com.lozano.application.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final ICouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponResponseDTO>> getAllCoupons() {
        return ResponseEntity.ok(couponService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CouponResponseDTO> getCouponByCode(
            @PathVariable String code) {
        return ResponseEntity.ok(couponService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<CouponResponseDTO> createCoupon(
            @RequestBody CouponRequestDTO request) {
        return ResponseEntity.status(201).body(couponService.create(request));
    }

    @PutMapping("/{code}")
    public ResponseEntity<CouponResponseDTO> updateCoupon(
            @PathVariable String code,
            @RequestBody CouponRequestDTO request) {
        return ResponseEntity.ok(couponService.update(code, request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoupon(
            @PathVariable String code) {
        couponService.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}/apply")
    public ResponseEntity<Double> applyCouponDiscount(
            @PathVariable String code,
            @RequestParam double subtotal) {
        return ResponseEntity.ok(couponService.applyDiscount(code, subtotal));
    }
}
