package com.lozano.application.mapper;

import com.lozano.application.dto.Product.ProductResponseDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.domain.entity.Product;
import com.lozano.domain.entity.SaleDetail;
import org.springframework.stereotype.Component;

@Component
public class SaleDetailMapper {

    public SaleDetailResponseDTO toResponse(SaleDetail saleDetail) {
        if (saleDetail == null) return null;
        return SaleDetailResponseDTO.builder()
                .id(saleDetail.getId())
                .product(ProductResponseDTO.builder()
                        .id(saleDetail.getProduct().getId())
                        .name(saleDetail.getProduct().getName())
                        .build())
                .quantity(saleDetail.getQuantity())
                .price(saleDetail.getPrice())
                .build();
    }

    public SaleDetail toEntity(SaleDetailRequestDTO dto, Product product) {
        if (dto == null) return null;
        return SaleDetail.builder()
                .product(product)
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
