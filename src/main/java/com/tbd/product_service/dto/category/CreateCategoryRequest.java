package com.tbd.product_service.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {

    @NotBlank(message = "error.product.category.name.notblank")
    @Size(min = 2, max = 30, message = "error.product.category.name.length")
    private String name;
    private Long parentId;      // null for root category
}
