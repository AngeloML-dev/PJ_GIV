package com.lozano.application.dto.Sale;

import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Sale details cannot be empty")
    private List<@Valid SaleDetailRequestDTO> details;

    @Pattern(regexp = "^[A-Z0-9-]{5,20}$", message = "Formato de código inválido")
    private String couponCode;
}
