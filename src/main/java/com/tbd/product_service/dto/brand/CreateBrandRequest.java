package com.tbd.product_service.dto.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

    @NotBlank(message = "error.product.brand.name.notblank")
    @Size(min = 2, max = 30, message = "error.product.brand.name.length")
    private String name;

    @Size(max = 500, message = "error.product.brand.description.length")
    private String description;

    @Size(max = 200, message = "error.product.brand.logoUrl.length")
    private String logoUrl;

    @Size(max = 200, message = "error.product.brand.websiteUrl.length")
    private String websiteUrl;
}
