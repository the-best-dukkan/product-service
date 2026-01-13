package com.tbd.product_service.entity;

import com.tbd.common.audit.Auditable;
import com.tbd.product_service.enums.BrandStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 300, nullable = false)
    private String name;

    @Column(name = "logo_url", length = 200)
    private String logoUrl;
    @Column(length = 500)
    private String description;
    @Column(name = "website_url", length = 200)
    private String websiteUrl;

    private String slug;

    @Enumerated(EnumType.STRING)
    private BrandStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != ProxyUtils.getUserClass(o)) return false;
        Brand that = (Brand) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
