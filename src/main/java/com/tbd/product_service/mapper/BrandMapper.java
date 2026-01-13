package com.tbd.product_service.mapper;

import com.tbd.product_service.dto.brand.CreateBrandRequest;
import com.tbd.product_service.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand createRequestToBrand(CreateBrandRequest createBrandRequest);

}
