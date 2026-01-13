package com.tbd.product_service.controller;

import com.tbd.product_service.dto.CreateResponse;
import com.tbd.product_service.dto.brand.CreateBrandRequest;
import com.tbd.product_service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CATALOG_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<CreateResponse> addBrand(@RequestBody CreateBrandRequest request) {

        return new ResponseEntity<>(
                brandService.addBrand(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/creation-request")
    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_CATALOG_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<CreateResponse> requestBrandCreation(@RequestBody CreateBrandRequest request) {

        return new ResponseEntity<>(
                brandService.requestBrandCreation(request),
                HttpStatus.CREATED
        );
    }
}
