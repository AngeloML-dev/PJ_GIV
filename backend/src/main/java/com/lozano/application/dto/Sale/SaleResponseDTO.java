package com.lozano.application.dto.Sale;

import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.application.dto.User.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private LocalDateTime saleDate;
    private Double total;
    private List<SaleDetailResponseDTO> details;
}
