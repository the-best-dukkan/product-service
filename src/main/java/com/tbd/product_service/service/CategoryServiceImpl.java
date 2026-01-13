package com.tbd.product_service.service;

import com.tbd.common.exceptions.ResourceNotFoundInDbException;
import com.tbd.common.exceptions.ValidationException;
import com.tbd.common.utils.CommonUtil;
import com.tbd.common.utils.Translator;
import com.tbd.product_service.dto.CreateCategoryRequest;
import com.tbd.product_service.entity.Category;
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
    public Category createCategory(CreateCategoryRequest request) {

        Category category = new Category();
        if (!StringUtils.isBlank(request.getName())) {
            category.setName(request.getName().toLowerCase().trim());
        } else {
            throw new ValidationException(translator.translate("error.product.category.name.notblank"));
        }
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

        category.setIsLeaf(true);
        Category savedCategory = categoryRepository.save(category);

        savedCategory.setPath(savedCategory.getPath() + savedCategory.getId().toString());

        return categoryRepository.save(savedCategory);
    }
}
