package com.ecommerce.ecommerce_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductDTOTest {

    @Test
    void testProductDTO() {
        // Arrange
        Long id = 1L;
        String title = "ProductTitle";
        String description = "ProductDescription";
        double price = 100.0;
        int stock = 10;
        String image = "ImageURL";
        BrandDTO brand = BrandDTO.builder().id(1L).name("BrandName").description("BrandDescription").build();
        CategoryDTO category = CategoryDTO.builder().id(1L).name("CategoryName").description("CategoryDescription").build();

        // Act
        ProductDTO productDTO = ProductDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .price(price)
                .stock(stock)
                .image(image)
                .brand(brand)
                .category(category)
                .build();

        // Assert
        assertNotNull(productDTO);
        assertEquals(id, productDTO.getId());
        assertEquals(title, productDTO.getTitle());
        assertEquals(description, productDTO.getDescription());
        assertEquals(price, productDTO.getPrice());
        assertEquals(stock, productDTO.getStock());
        assertEquals(image, productDTO.getImage());
        assertEquals(brand, productDTO.getBrand());
        assertEquals(category, productDTO.getCategory());
    }
}
