package com.lozano.application.mapper;

import com.lozano.application.dto.Product.ProductResponseDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.domain.entity.Product;
import com.lozano.domain.entity.SaleDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleDetailMapper {

    public SaleDetailResponseDTO toResponseDTO(SaleDetail detail) {
        if (detail == null) return null;

        return SaleDetailResponseDTO.builder()
                .id(detail.getId())
                .productId(detail.getProduct().getId())
                .productName(detail.getProduct().getName())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getUnitPrice())
                .subtotal(detail.getSubtotal())
                .build();
    }

    public SaleDetail toEntity(SaleDetailRequestDTO dto, Product product) {
        if (dto == null || product == null) return null;

        return SaleDetail.builder()
                .product(product)
                .quantity(dto.getQuantity())
                .unitPrice(product.getPrice()) // Precio actual del producto
                .build();
    }
    //Actualiza una entidad SaleDetail existente
    public void updateEntityFromDTO(SaleDetailRequestDTO dto, SaleDetail detail, Product product) {
        detail.setProduct(product);
        detail.setQuantity(dto.getQuantity());
        detail.setUnitPrice(product.getPrice());
    }
}
