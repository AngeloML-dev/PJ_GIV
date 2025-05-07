package com.lozano.application.dto.Sale;

import com.lozano.application.dto.Coupon.CouponResponseDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {
    private Long id;
    private LocalDateTime saleDate;
    private Double subtotal;
    private Double discountAmount;
    private Double total;
    private String couponCode;

    // User info
    private Long userId;
    private String userUsername;

    // Detalles de la venta
    private List<SaleDetailResponseDTO> details;

    // Cupón aplicado (si existe)
    private CouponResponseDTO coupon;
}
