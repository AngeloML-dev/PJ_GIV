package com.lozano.application.dto.Sale;

import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {
    private Long userId;
    private List<SaleDetailRequestDTO> saleDetails;
}
