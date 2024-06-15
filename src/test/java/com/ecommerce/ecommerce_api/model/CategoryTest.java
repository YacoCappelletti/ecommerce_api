package com.ecommerce.ecommerce_api.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Test
    void testCategoryModel() {
        // Arrange
        Long id = 1L;
        String name = "CategoryName";
        String description = "CategoryDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Category category = Category.builder()
                .category_id(id)
                .name(name)
                .description(description)
                .products(products)
                .build();

        // Assert
        assertNotNull(category);
        assertEquals(id, category.getCategory_id());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
        assertEquals(products, category.getProducts());
    }

    @Test
    void testCategoryNoArgsConstructor() {
        // Act
        Category category = new Category();

        // Assert
        assertNotNull(category);
    }

    @Test
    void testCategoryAllArgsConstructor() {
        // Arrange
        Long id = 1L;
        String name = "CategoryName";
        String description = "CategoryDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Category category = new Category(id, name, description, products);

        // Assert
        assertNotNull(category);
        assertEquals(id, category.getCategory_id());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
        assertEquals(products, category.getProducts());
    }

    @Test
    void testCategorySettersAndGetters() {
        // Arrange
        Category category = new Category();
        Long id = 1L;
        String name = "CategoryName";
        String description = "CategoryDescription";
        List<Product> products = Collections.singletonList(new Product());

        // Act
        category.setCategory_id(id);
        category.setName(name);
        category.setDescription(description);
        category.setProducts(products);

        // Assert
        assertEquals(id, category.getCategory_id());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
        assertEquals(products, category.getProducts());
    }
}
