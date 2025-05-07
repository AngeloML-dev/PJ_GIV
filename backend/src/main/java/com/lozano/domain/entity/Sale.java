package com.lozano.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "sale_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime saleDate;

    @Column(nullable = false, precision = 10)
    private Double subtotal;

    @Column(precision = 10)
    @Builder.Default
    private Double discountAmount = 0.0;

    @Column(nullable = false, precision = 10)
    private Double total;

    @Column(length = 20)
    private String couponCode;

    @Transient
    @Setter(AccessLevel.NONE)
    private Coupon coupon;

    @Builder.Default
    @OneToMany(
            mappedBy = "sale",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SaleDetail> details = new ArrayList<>();

    // ========== MÉTODOS DE NEGOCIO ==========
    public void applyCouponDiscount(Coupon coupon) {
        this.coupon = coupon;
        this.couponCode = (coupon != null) ? coupon.getCode() : null;
        calculateTotals(); // Recalcula todos los valores
    }

    public void calculateTotals() {
        this.subtotal = calculateSubtotal();
        this.discountAmount = calculateDiscount();
        this.total = this.subtotal - this.discountAmount;
    }

    private Double calculateSubtotal() {
        return details.stream()
                .mapToDouble(d -> d.getUnitPrice() * d.getQuantity())
                .sum();
    }

    private Double calculateDiscount() {
        if (this.coupon == null || !this.coupon.isActive()) {
            return 0.0;
        }

        double subtotal = calculateSubtotal();

        return switch (this.coupon.getDiscountType().toLowerCase()) {
            case "percentage" -> subtotal * (this.coupon.getValue() / 100);
            case "fixed_amount" -> Math.min(this.coupon.getValue(), subtotal);
            default -> 0.0;
        };
    }

    public void addDetail(SaleDetail detail) {
        details.add(detail);
        detail.setSale(this);
        calculateTotals();
    }

    public void removeDetail(SaleDetail detail) {
        details.remove(detail);
        detail.setSale(null);
        calculateTotals();
    }

    @PrePersist
    protected void onCreate() {
        if (this.saleDate == null) {
            this.saleDate = LocalDateTime.now();
        }
        calculateTotals();
    }

    // ========== MÉTODOS DE CONVENIENCIA ==========
    public boolean hasCouponApplied() {
        return this.coupon != null && this.couponCode != null;
    }
}
