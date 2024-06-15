package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandConverterTest {

    @Test
    void testToDTO() {
        // Arrange
        Brand brand = Brand.builder()
                .id(1L)
                .name("Test Brand")
                .description("Test Description")
                .build();

        // Act
        BrandDTO brandDTO = BrandConverter.toDTO(brand);

        // Assert
        assertNotNull(brandDTO);
        assertEquals(1L, brandDTO.getId());
        assertEquals("Test Brand", brandDTO.getName());
        assertEquals("Test Description", brandDTO.getDescription());
    }

    @Test
    void testToEntity() {
        // Arrange
        BrandDTO brandDTO = BrandDTO.builder()
                .id(1L)
                .name("Test Brand")
                .description("Test Description")
                .build();

        // Act
        Brand brand = BrandConverter.toEntity(brandDTO);

        // Assert
        assertNotNull(brand);
        assertEquals(1L, brand.getId());
        assertEquals("Test Brand", brand.getName());
        assertEquals("Test Description", brand.getDescription());
    }

    @Test
    void testToDTOList() {
        // Arrange
        Brand brand1 = Brand.builder()
                .id(1L)
                .name("Test Brand 1")
                .description("Test Description 1")
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .name("Test Brand 2")
                .description("Test Description 2")
                .build();
        List<Brand> brands = Arrays.asList(brand1, brand2);

        // Act
        List<BrandDTO> brandDTOs = BrandConverter.toDTOList(brands);

        // Assert
        assertNotNull(brandDTOs);
        assertEquals(2, brandDTOs.size());
        assertEquals(1L, brandDTOs.get(0).getId());
        assertEquals("Test Brand 1", brandDTOs.get(0).getName());
        assertEquals("Test Description 1", brandDTOs.get(0).getDescription());
        assertEquals(2L, brandDTOs.get(1).getId());
        assertEquals("Test Brand 2", brandDTOs.get(1).getName());
        assertEquals("Test Description 2", brandDTOs.get(1).getDescription());
    }

    @Test
    void testToEntityList() {
        // Arrange
        BrandDTO brandDTO1 = BrandDTO.builder()
                .id(1L)
                .name("Test Brand 1")
                .description("Test Description 1")
                .build();
        BrandDTO brandDTO2 = BrandDTO.builder()
                .id(2L)
                .name("Test Brand 2")
                .description("Test Description 2")
                .build();
        List<BrandDTO> brandDTOs = Arrays.asList(brandDTO1, brandDTO2);

        // Act
        List<Brand> brands = BrandConverter.toEntityList(brandDTOs);

        // Assert
        assertNotNull(brands);
        assertEquals(2, brands.size());
        assertEquals(1L, brands.get(0).getId());
        assertEquals("Test Brand 1", brands.get(0).getName());
        assertEquals("Test Description 1", brands.get(0).getDescription());
        assertEquals(2L, brands.get(1).getId());
        assertEquals("Test Brand 2", brands.get(1).getName());
        assertEquals("Test Description 2", brands.get(1).getDescription());
    }
}
