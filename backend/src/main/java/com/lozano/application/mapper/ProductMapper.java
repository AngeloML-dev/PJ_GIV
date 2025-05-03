package com.lozano.application.mapper;

import com.lozano.application.dto.Product.ProductRequestDTO;
import com.lozano.application.dto.Product.ProductResponseDTO;
import com.lozano.domain.entity.Category;
import com.lozano.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDTO toResponse(Product product) {
        if (product == null) return null;
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }

    public Product toEntity(ProductRequestDTO dto, Category category) {
        if (dto == null) return null;
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(category)
                .build();
    }
}
