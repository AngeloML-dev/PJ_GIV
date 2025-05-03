package com.lozano.application.dto.SaleDetail;

import com.lozano.application.dto.Product.ProductResponseDTO;
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
    private ProductResponseDTO product;
    private Integer quantity;
    private Double price;
}
