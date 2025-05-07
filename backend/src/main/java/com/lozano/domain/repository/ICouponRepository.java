package com.lozano.domain.repository;

import com.lozano.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {

    // Método por convención de Spring Data JPA
    Optional<Coupon> findByCodeAndActiveTrue(String code);

    // Métodos adicionales necesarios
    Optional<Coupon> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);

    // Consulta JPQL personalizada (alternativa al método por convención)
    @Query("SELECT c FROM Coupon c WHERE c.code = :code AND c.active = true")
    Optional<Coupon> findValidCoupon(@Param("code") String code);
}
