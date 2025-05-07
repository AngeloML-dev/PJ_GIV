package com.lozano.domain.repository;

import com.lozano.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    // Búsqueda por rango de fechas
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Búsqueda por usuario
    List<Sale> findByUserId(Long userId);

    // Búsqueda por cupón aplicado
    List<Sale> findByCouponCode(String couponCode);

    // Consulta personalizada para reportes
    @Query("SELECT s FROM Sale s WHERE s.total >= :minAmount")
    List<Sale> findSalesAboveAmount(@Param("minAmount") Double minAmount);

    // Verifica si existe una venta para un usuario específico
    boolean existsByIdAndUserId(Long saleId, Long userId);

    // Ejemplo: Ventas con descuento aplicado
    @Query("SELECT s FROM Sale s WHERE s.discountAmount > 0")
    List<Sale> findDiscountedSales();
}
