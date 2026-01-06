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
import org.springframework.data.util.ProxyUtils;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        ProductSearchDocument that = (ProductSearchDocument) o;
        return productId != null && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
