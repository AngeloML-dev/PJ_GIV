package com.lozano.application.service;

import com.lozano.application.dto.Coupon.CouponRequestDTO;
import com.lozano.application.dto.Coupon.CouponResponseDTO;

import java.util.List;

public interface ICouponService {
    List<CouponResponseDTO> findAll();
    CouponResponseDTO findByCode(String code);
    CouponResponseDTO create(CouponRequestDTO dto);
    CouponResponseDTO update(String code, CouponRequestDTO dto);
    void deleteByCode(String code);
    double applyDiscount(String code, double subtotal);
}
