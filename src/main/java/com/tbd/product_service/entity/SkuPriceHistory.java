package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sku_price_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuPriceHistory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long skuId;

    private BigDecimal oldPrice;
    private BigDecimal newPrice;

    private Instant changedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        SkuPriceHistory that = (SkuPriceHistory) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
