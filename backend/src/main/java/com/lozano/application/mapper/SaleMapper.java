package com.lozano.application.mapper;

import com.lozano.application.dto.Sale.SaleRequestDTO;
import com.lozano.application.dto.Sale.SaleResponseDTO;
import com.lozano.domain.entity.Sale;
import com.lozano.domain.entity.SaleDetail;
import com.lozano.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final UserMapper userMapper;
    private final SaleDetailMapper saleDetailMapper;

    public SaleResponseDTO toResponse(Sale sale) {
        if (sale == null) return null;
        return SaleResponseDTO.builder()
                .id(sale.getId())
                .saleDate(sale.getSaleDate())
                .total(sale.getTotal())
                .user(userMapper.toResponse(sale.getUser()))
                .details(sale.getDetails().stream()
                        .map(saleDetailMapper::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public Sale toEntity(SaleRequestDTO dto, User user, List<SaleDetail> details) {
        if (dto == null) return null;
        return Sale.builder()
                .user(user)
                .saleDate(LocalDateTime.now())
                .details(details)
                .build();
    }
}
