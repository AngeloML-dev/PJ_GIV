package com.lozano.domain.repository;

import com.lozano.domain.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    // Busca todos los detalles de una venta específica
    List<SaleDetail> findBySaleId(Long saleId);

    // Consulta optimizada para productos más vendidos
    @Query("SELECT sd.product.id, SUM(sd.quantity) as totalQuantity " +
            "FROM SaleDetail sd " +
            "GROUP BY sd.product.id " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTopSoldProducts();

    // Busca detalles por producto
    List<SaleDetail> findByProductId(Long productId);

    // Consulta para análisis de ventas
    @Query("SELECT sd FROM SaleDetail sd WHERE sd.sale.saleDate BETWEEN :start AND :end")
    List<SaleDetail> findDetailsByDateRange(
            @Param("start") LocalDateTime startDate,
            @Param("end") LocalDateTime endDate);

    // Total vendido por categoría
    @Query("SELECT p.category.name, SUM(sd.subtotal) " +
            "FROM SaleDetail sd JOIN sd.product p " +
            "GROUP BY p.category.name")
    List<Object[]> getSalesByCategory();
    //Validar que un detalle pertenece a una venta antes de hacer operaciones
    @Query("SELECT CASE WHEN COUNT(sd) > 0 THEN true ELSE false END " +
            "FROM SaleDetail sd WHERE sd.id = :detailId AND sd.sale.id = :saleId")
    boolean existsByIdAndSaleId(@Param("detailId") Long detailId,
                                @Param("saleId") Long saleId);
}
