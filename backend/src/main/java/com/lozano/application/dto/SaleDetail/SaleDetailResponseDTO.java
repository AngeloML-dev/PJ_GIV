package com.lozano.application.dto.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String productDescription; // Opcional
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
