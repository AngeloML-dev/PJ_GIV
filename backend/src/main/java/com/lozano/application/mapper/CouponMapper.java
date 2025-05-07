package com.lozano.application.mapper;

import com.lozano.application.dto.Coupon.CouponRequestDTO;
import com.lozano.application.dto.Coupon.CouponResponseDTO;
import com.lozano.domain.entity.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {
    public Coupon toEntity(CouponRequestDTO dto) {
        return Coupon.builder()
                .code(dto.getCode())
                .discountType(dto.getDiscountType())
                .value(dto.getValue())
                .active(true)
                .build();
    }

    public CouponResponseDTO toResponseDTO(Coupon coupon) {
        return CouponResponseDTO.builder()
                .code(coupon.getCode())
                .discountType(coupon.getDiscountType())
                .value(coupon.getValue())
                .status(coupon.isActive() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
