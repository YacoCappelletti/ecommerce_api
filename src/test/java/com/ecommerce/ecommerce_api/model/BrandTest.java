package com.ecommerce.ecommerce_api.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandTest {

    @Test
    void testBrandModel() {
        // Arrange
        Long id = 1L;
        String name = "BrandName";
        String description = "BrandDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Brand brand = Brand.builder()
                .id(id)
                .name(name)
                .description(description)
                .products(products)
                .build();

        // Assert
        assertNotNull(brand);
        assertEquals(id, brand.getId());
        assertEquals(name, brand.getName());
        assertEquals(description, brand.getDescription());
        assertEquals(products, brand.getProducts());
    }

    @Test
    void testBrandNoArgsConstructor() {
        // Act
        Brand brand = new Brand();

        // Assert
        assertNotNull(brand);
    }

    @Test
    void testBrandAllArgsConstructor() {
        // Arrange
        Long id = 1L;
        String name = "BrandName";
        String description = "BrandDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Brand brand = new Brand(id, name, description, products);

        // Assert
        assertNotNull(brand);
        assertEquals(id, brand.getId());
        assertEquals(name, brand.getName());
        assertEquals(description, brand.getDescription());
        assertEquals(products, brand.getProducts());
    }

    @Test
    void testBrandSettersAndGetters() {
        // Arrange
        Brand brand = new Brand();
        Long id = 1L;
        String name = "BrandName";
        String description = "BrandDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        brand.setId(id);
        brand.setName(name);
        brand.setDescription(description);
        brand.setProducts(products);

        // Assert
        assertEquals(id, brand.getId());
        assertEquals(name, brand.getName());
        assertEquals(description, brand.getDescription());
        assertEquals(products, brand.getProducts());
    }
}
