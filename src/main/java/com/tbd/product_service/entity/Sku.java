package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.enums.SkuStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sku")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sku extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_code", unique = true, nullable = false)
    private String skuCode;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private SkuStatus status;

    private Double weight;
    private Double length;
    private Double width;
    private Double height;
}
