package com.tbd.product_service.service;

import com.tbd.product_service.dto.CreateResponse;
import com.tbd.product_service.dto.category.CreateCategoryRequest;

public interface CategoryService {

    CreateResponse createCategory(CreateCategoryRequest request);
}
