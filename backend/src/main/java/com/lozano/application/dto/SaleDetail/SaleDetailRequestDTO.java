package com.lozano.application.dto.SaleDetail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailRequestDTO {
    @NotNull(message = "ID de venta es obligatorio")
    private Long saleId;  // ✅ Campo requerido

    @NotNull(message = "ID de producto es obligatorio")
    private Long productId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @NotNull(message = "La cantidad es obligatoria")
    private Integer quantity;
}
