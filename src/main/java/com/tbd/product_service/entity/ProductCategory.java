package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.entity.composite_key.ProductCategoryId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductCategoryId.class)
public class ProductCategory extends Auditable {

    @Id
    private Long productId;

    @Id
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        ProductCategory that = (ProductCategory) o;
        return productId != null && productId.equals(that.productId) && categoryId != null && categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
