package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sku_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuImage extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sku_id")
    private Sku sku;

    private String url;
}
