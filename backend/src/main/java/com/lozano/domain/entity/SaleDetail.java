package com.lozano.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "sale_detail_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}
