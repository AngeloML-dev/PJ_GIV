package com.lozano.application.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer minStock;
    private Long categoryId;
}
