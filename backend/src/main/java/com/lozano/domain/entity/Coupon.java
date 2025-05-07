package com.lozano.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    private String code;  // Example: "SUMMER10"

    @Column(nullable = false)
    private String discountType;    // "percentage" or "fixed_amount"

    @Column(nullable = false)
    private double value;   // 10 (if percentage) or 50 (if fixed amount)

    @Builder.Default
    private boolean active = true;
}
