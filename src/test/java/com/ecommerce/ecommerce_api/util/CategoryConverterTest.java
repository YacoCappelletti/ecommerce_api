package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryConverterTest {

    @Test
    void testToDTO() {
        // Arrange
        Category category = Category.builder()
                .category_id(1L)
                .name("Test Category")
                .description("Test Description")
                .build();

        // Act
        CategoryDTO categoryDTO = CategoryConverter.toDTO(category);

        // Assert
        assertNotNull(categoryDTO);
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Test Category", categoryDTO.getName());
        assertEquals("Test Description", categoryDTO.getDescription());
    }

    @Test
    void testToEntity() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Test Category")
                .description("Test Description")
                .build();

        // Act
        Category category = CategoryConverter.toEntity(categoryDTO);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getCategory_id());
        assertEquals("Test Category", category.getName());
        assertEquals("Test Description", category.getDescription());
    }

    @Test
    void testToDTOList() {
        // Arrange
        Category category1 = Category.builder()
                .category_id(1L)
                .name("Test Category 1")
                .description("Test Description 1")
                .build();
        Category category2 = Category.builder()
                .category_id(2L)
                .name("Test Category 2")
                .description("Test Description 2")
                .build();
        List<Category> categories = Arrays.asList(category1, category2);

        // Act
        List<CategoryDTO> categoryDTOs = CategoryConverter.toDTOList(categories);

        // Assert
        assertNotNull(categoryDTOs);
        assertEquals(2, categoryDTOs.size());
        assertEquals(1L, categoryDTOs.get(0).getId());
        assertEquals("Test Category 1", categoryDTOs.get(0).getName());
        assertEquals("Test Description 1", categoryDTOs.get(0).getDescription());
        assertEquals(2L, categoryDTOs.get(1).getId());
        assertEquals("Test Category 2", categoryDTOs.get(1).getName());
        assertEquals("Test Description 2", categoryDTOs.get(1).getDescription());
    }

    @Test
    void testToEntityList() {
        // Arrange
        CategoryDTO categoryDTO1 = CategoryDTO.builder()
                .id(1L)
                .name("Test Category 1")
                .description("Test Description 1")
                .build();
        CategoryDTO categoryDTO2 = CategoryDTO.builder()
                .id(2L)
                .name("Test Category 2")
                .description("Test Description 2")
                .build();
        List<CategoryDTO> categoryDTOs = Arrays.asList(categoryDTO1, categoryDTO2);

        // Act
        List<Category> categories = CategoryConverter.toEntityList(categoryDTOs);

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals(1L, categories.get(0).getCategory_id());
        assertEquals("Test Category 1", categories.get(0).getName());
        assertEquals("Test Description 1", categories.get(0).getDescription());
        assertEquals(2L, categories.get(1).getCategory_id());
        assertEquals("Test Category 2", categories.get(1).getName());
        assertEquals("Test Description 2", categories.get(1).getDescription());
    }
}
