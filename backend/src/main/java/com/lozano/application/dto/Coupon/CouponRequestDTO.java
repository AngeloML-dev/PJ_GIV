package com.lozano.application.dto.Coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequestDTO {

    private String code;
    private String discountType;
    private double value;
}
