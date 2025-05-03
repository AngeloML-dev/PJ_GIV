package com.lozano.application.dto.Product;

import com.lozano.application.dto.Category.CategoryResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer minStock;
    private Long categoryId;
    private String categoryName;
}
