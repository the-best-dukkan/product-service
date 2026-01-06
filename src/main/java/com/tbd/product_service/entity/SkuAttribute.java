package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.entity.composite_key.SkuAttributeId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sku_attribute")
@IdClass(SkuAttributeId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuAttribute extends Auditable {

    @Id
    private Long skuId;

    @Id
    private String attributeName;

    private String attributeValue;
}
