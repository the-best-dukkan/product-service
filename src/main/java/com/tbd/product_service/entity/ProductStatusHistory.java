package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

import java.time.Instant;

@Entity
@Table(name = "product_status_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusHistory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private ProductStatus newStatus;

    private Instant changedAt;
    private String changedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        ProductStatusHistory that = (ProductStatusHistory) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
