package com.tbd.product_service.service;

import com.tbd.product_service.dto.CreateCategoryRequest;
import com.tbd.product_service.entity.Category;

public interface CategoryService {

    Category createCategory(CreateCategoryRequest request);
}
