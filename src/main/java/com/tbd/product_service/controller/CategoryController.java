package com.tbd.product_service.controller;

import com.tbd.common.utils.Translator;
import com.tbd.product_service.dto.CreateCategoryRequest;
import com.tbd.product_service.dto.CreateResponse;
import com.tbd.product_service.entity.Category;
import com.tbd.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Translator translator;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CATALOG_ADMIN', 'ADMIN')")
    public ResponseEntity<CreateResponse> addCategory(@RequestBody CreateCategoryRequest request) {

        Category category = categoryService.createCategory(request);
        return new ResponseEntity<>(
                CreateResponse.builder().id(category.getId()).message(translator.translate("product.category.created.success")).build(),
                HttpStatus.CREATED
        );
    }
}
