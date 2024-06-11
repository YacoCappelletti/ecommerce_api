package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {
    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getCategory_id())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .category_id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(CategoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Category> toEntityList(List<CategoryDTO> categoryDTOs) {
        return categoryDTOs.stream()
                .map(CategoryConverter::toEntity)
                .collect(Collectors.toList());
    }
}
