package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.enums.PriceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    private Sku sku;

    @Enumerated(EnumType.STRING)
    private PriceType priceType;

    @Column(nullable = false)
    private BigDecimal amount;
    private String currency;

    private Instant validFrom;
    private Instant validTo;
}
