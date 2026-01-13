package com.tbd.product_service.service;

import com.tbd.product_service.dto.brand.CreateBrandRequest;
import com.tbd.product_service.dto.CreateResponse;

public interface BrandService {

    CreateResponse addBrand(CreateBrandRequest request);
    CreateResponse requestBrandCreation(CreateBrandRequest request);
}
