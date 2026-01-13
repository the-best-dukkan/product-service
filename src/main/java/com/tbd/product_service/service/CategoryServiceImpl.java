package com.tbd.product_service.service;

import com.tbd.common.exceptions.ResourceNotFoundInDbException;
import com.tbd.common.exceptions.UniqueKeyViolationException;
import com.tbd.common.exceptions.ValidationException;
import com.tbd.common.utils.CommonUtil;
import com.tbd.common.utils.Translator;
import com.tbd.product_service.dto.CreateResponse;
import com.tbd.product_service.dto.category.CreateCategoryRequest;
import com.tbd.product_service.entity.Category;
import com.tbd.product_service.enums.CategoryStatus;
import com.tbd.product_service.repository.CategoryRepository;
import com.tbd.product_service.util.SlugGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Translator translator;

    @Override
    @Transactional
    public CreateResponse createCategory(CreateCategoryRequest request) {

        Category category = new Category();

        handleCategoryName(request, category);

        category.setSlug(SlugGenerator.generate(request.getName()));

        if (request.getParentId() == null) {

            category.setLevel(0);
            category.setParentId(null);
            category.setPath(StringUtils.EMPTY);
        } else {

            if (!CommonUtil.validateId(request.getParentId())) {
                throw new ValidationException(translator.translate("error.request.invalid.id"));
            }

            Category parentCategory = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundInDbException(translator.translate("error.product.category.parent_id.notfound", request.getParentId())));

            category.setParentId(parentCategory.getId());
            category.setLevel(parentCategory.getLevel() + 1);
            category.setPath(parentCategory.getPath() + "/");

            parentCategory.setIsLeaf(false);
            categoryRepository.save(category);
        }

        category.setStatus(CategoryStatus.ACTIVE);
        category.setIsLeaf(true);
        Category savedCategory = categoryRepository.save(category);

        savedCategory.setPath(savedCategory.getPath() + savedCategory.getId().toString());

        categoryRepository.save(savedCategory);

        return CreateResponse.builder()
                .id(savedCategory.getId())
                .message(translator.translate("product.category.created.success"))
                .build();
    }

    private void handleCategoryName(CreateCategoryRequest request, Category category) {

        if (!StringUtils.isBlank(request.getName())) {
            String name = request.getName().toUpperCase().trim();

            if (!categoryRepository.existsByName(name)) {
                category.setName(name);
            }else {
                throw new UniqueKeyViolationException(translator.translate("error.request.category.name.exists", request.getName()));
            }
        } else {
            throw new ValidationException(translator.translate("error.product.category.name.notblank"));
        }
    }
}
