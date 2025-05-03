package com.lozano.application.dto.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailRequestDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}
