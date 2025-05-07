package com.lozano.application.dto.Coupon;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponResponseDTO {
    private String code;
    private String discountType;
    private double value;
    private String status; // "ACTIVE" or "INACTIVE"
}
