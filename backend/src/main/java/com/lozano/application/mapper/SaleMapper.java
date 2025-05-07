package com.lozano.application.mapper;

import com.lozano.application.dto.Coupon.CouponResponseDTO;
import com.lozano.application.dto.Sale.SaleRequestDTO;
import com.lozano.application.dto.Sale.SaleResponseDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.domain.entity.*;
import com.lozano.domain.repository.ICouponRepository;
import com.lozano.domain.repository.IProductRepository;
import com.lozano.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final IUserRepository userRepository;
    private final ICouponRepository couponRepository;
    private final IProductRepository productRepository;
    private final SaleDetailMapper saleDetailMapper;

    public SaleResponseDTO toResponseDTO(Sale sale) {
        if (sale == null) return null;

        CouponResponseDTO couponResponse = null;
        if (StringUtils.hasText(sale.getCouponCode())) {
            couponResponse = couponRepository.findByCode(sale.getCouponCode())
                    .map(this::mapCouponToResponseDTO)
                    .orElse(null);
        }

        return SaleResponseDTO.builder()
                .id(sale.getId())
                .userId(sale.getUser().getId())
                .userUsername(sale.getUser().getUsername()) // Campo corregido
                .saleDate(sale.getSaleDate())
                .subtotal(sale.getSubtotal())
                .discountAmount(sale.getDiscountAmount())
                .total(sale.getTotal())
                .couponCode(sale.getCouponCode())
                .coupon(couponResponse)
                .details(mapDetailsToDTO(sale.getDetails()))
                .build();
    }

    private CouponResponseDTO mapCouponToResponseDTO(Coupon coupon) {
        if (coupon == null) return null;

        return CouponResponseDTO.builder()
                .code(coupon.getCode())
                .discountType(coupon.getDiscountType())
                .value(coupon.getValue())
                .status(coupon.isActive() ? "ACTIVE" : "INACTIVE")
                .build();
    }

    public Sale toEntity(SaleRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Manejo de cupón
        Coupon coupon = null;
        if (StringUtils.hasText(dto.getCouponCode())) {
            coupon = couponRepository.findByCode(dto.getCouponCode())
                    .orElseThrow(() -> new RuntimeException("Cupón no válido"));
        }

        // Mapeo de detalles con productos
        List<SaleDetail> details = dto.getDetails().stream()
                .map(detailDto -> {
                    Product product = productRepository.findById(detailDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detailDto.getProductId()));
                    return saleDetailMapper.toEntity(detailDto, product);
                })
                .toList();

        Sale sale = Sale.builder()
                .user(user)
                .details(details)
                .build();

        if (coupon != null) {
            sale.applyCouponDiscount(coupon);
        }

        sale.calculateTotals();
        return sale;
    }

    public void updateEntityFromDTO(SaleRequestDTO dto, Sale sale) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualización de cupón
        Coupon coupon = null;
        if (StringUtils.hasText(dto.getCouponCode())) {
            coupon = couponRepository.findByCode(dto.getCouponCode())
                    .orElseThrow(() -> new RuntimeException("Cupón no válido"));
        }

        // Actualización de detalles
        List<SaleDetail> details = dto.getDetails().stream()
                .map(detailDto -> {
                    Product product = productRepository.findById(detailDto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detailDto.getProductId()));
                    return saleDetailMapper.toEntity(detailDto, product);
                })
                .toList();

        // Aplicar cambios
        sale.setUser(user);
        sale.setDetails(details);
        sale.getDetails().forEach(detail -> detail.setSale(sale)); // Mantener relación bidireccional

        if (coupon != null) {
            sale.applyCouponDiscount(coupon);
        } else {
            sale.applyCouponDiscount(null); // Remover cupón si es necesario
        }

        sale.calculateTotals();
    }

    private List<SaleDetailResponseDTO> mapDetailsToDTO(List<SaleDetail> details) {
        return details.stream()
                .map(saleDetailMapper::toResponseDTO)
                .toList();
    }
}
