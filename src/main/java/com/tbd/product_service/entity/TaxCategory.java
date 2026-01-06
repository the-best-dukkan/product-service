package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tax_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal percentage;
}
