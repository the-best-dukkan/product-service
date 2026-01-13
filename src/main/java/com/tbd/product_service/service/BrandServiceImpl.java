package com.tbd.product_service.service;

import com.tbd.common.exceptions.UniqueKeyViolationException;
import com.tbd.common.exceptions.ValidationException;
import com.tbd.common.utils.Translator;
import com.tbd.product_service.dto.CreateResponse;
import com.tbd.product_service.dto.brand.CreateBrandRequest;
import com.tbd.product_service.entity.Brand;
import com.tbd.product_service.enums.BrandStatus;
import com.tbd.product_service.mapper.BrandMapper;
import com.tbd.product_service.repository.BrandRepository;
import com.tbd.product_service.util.SlugGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final Translator translator;

    @Override
    public CreateResponse addBrand(CreateBrandRequest request) {

        Brand brand = createBrand(request);
        brand.setStatus(BrandStatus.ACTIVE);

        Brand savedBrand = brandRepository.save(brand);

        return CreateResponse.builder()
                .id(savedBrand.getId())
                .message(translator.translate("product.brand.created.success"))
                .build();
    }

    @Override
    public CreateResponse requestBrandCreation(CreateBrandRequest request) {

        Brand brand = createBrand(request);
        brand.setStatus(BrandStatus.PENDING_APPROVAL);

        Brand savedBrand = brandRepository.save(brand);

        return CreateResponse.builder()
                .id(savedBrand.getId())
                .message(translator.translate("product.brand.created.pending_approval"))
                .build();
    }

    private Brand createBrand(CreateBrandRequest request) {

        Brand brand = brandMapper.createRequestToBrand(request);

        handleBrandName(request, brand);

        brand.setSlug(SlugGenerator.generate(brand.getName()));

        return brand;
    }

    private void handleBrandName(CreateBrandRequest request, Brand brand) {

        if (!StringUtils.isBlank(request.getName())) {
            String name = request.getName().toUpperCase().trim();

            if (!brandRepository.existsByName(name)) {
                brand.setName(name);
            } else {
                throw new UniqueKeyViolationException(translator.translate("error.request.brand.name.exists", request.getName()));
            }
        } else {
            throw new ValidationException(translator.translate("error.product.brand.name.notblank"));
        }
    }
}
