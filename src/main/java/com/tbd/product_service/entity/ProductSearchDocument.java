package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_search_document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDocument extends Auditable {

    @Id
    private Long productId;

    private String title;
    private String brandName;
    private String categoryPath;

    @Column(columnDefinition = "jsonb")
    private String attributesJson;
}
