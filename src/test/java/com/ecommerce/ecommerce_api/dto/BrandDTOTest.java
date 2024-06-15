package com.ecommerce.ecommerce_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandDTOTest {

    @Test
    void testBrandDTO() {
        // Arrange
        Long id = 1L;
        String name = "BrandName";
        String description = "BrandDescription";

        // Act
        BrandDTO brandDTO = BrandDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();

        // Assert
        assertNotNull(brandDTO);
        assertEquals(id, brandDTO.getId());
        assertEquals(name, brandDTO.getName());
        assertEquals(description, brandDTO.getDescription());
    }
}
