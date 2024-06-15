package com.ecommerce.ecommerce_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryDTOTest {

    @Test
    void testCategoryDTO() {
        // Arrange
        Long id = 1L;
        String name = "CategoryName";
        String description = "CategoryDescription";

        // Act
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();

        // Assert
        assertNotNull(categoryDTO);
        assertEquals(id, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
        assertEquals(description, categoryDTO.getDescription());
    }
}
