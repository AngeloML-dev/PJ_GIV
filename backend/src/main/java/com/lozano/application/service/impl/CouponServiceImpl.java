package com.lozano.application.service.impl;

import com.lozano.application.dto.Coupon.CouponRequestDTO;
import com.lozano.application.dto.Coupon.CouponResponseDTO;
import com.lozano.application.mapper.CouponMapper;
import com.lozano.application.service.ICouponService;
import com.lozano.domain.entity.Coupon;
import com.lozano.domain.repository.ICouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService {

    private final ICouponRepository couponRepository;
    private final CouponMapper couponMapper;

    @Override
    public List<CouponResponseDTO> findAll() {
        List<Coupon> coupons = couponRepository.findAll();
        if (coupons.isEmpty()) {
            throw new RuntimeException("No coupons found");
        }
        return coupons.stream()
                .map(couponMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponseDTO findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Invalid coupon code");
        }
        return couponRepository.findByCodeAndActiveTrue(code)
                .map(couponMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    public CouponResponseDTO create(CouponRequestDTO dto) {
        validateCouponRequest(dto);

        if (couponRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Coupon code already exists");
        }

        Coupon coupon = couponMapper.toEntity(dto);
        return couponMapper.toResponseDTO(couponRepository.save(coupon));
    }

    @Override
    public CouponResponseDTO update(String code, CouponRequestDTO dto) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Invalid coupon code");
        }
        validateCouponRequest(dto);

        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setDiscountType(dto.getDiscountType());
        coupon.setValue(dto.getValue());

        return couponMapper.toResponseDTO(couponRepository.save(coupon));
    }

    @Override
    public void deleteByCode(String code) {
        if (!couponRepository.existsByCode(code)) {
            throw new RuntimeException("Coupon not found");
        }
        couponRepository.deleteByCode(code);
    }

    @Override
    public double applyDiscount(String code, double subtotal) {
        Coupon coupon = couponRepository.findValidCoupon(code)
                .orElseThrow(() -> new RuntimeException("Invalid or inactive coupon"));

        return coupon.getDiscountType().equals("percentage")
                ? subtotal * (coupon.getValue() / 100)
                : coupon.getValue();
    }

    private void validateCouponRequest(CouponRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Coupon DTO cannot be null");
        }
        if (!StringUtils.hasText(dto.getCode())) {
            throw new IllegalArgumentException("Coupon code cannot be empty");
        }
        if (!StringUtils.hasText(dto.getDiscountType())) {
            throw new IllegalArgumentException("Discount type cannot be empty");
        }
        if (dto.getValue() <= 0) {
            throw new IllegalArgumentException("Coupon value must be positive");
        }
    }
}
