package com.ecommerce.ecommerce_api.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {

    @Test
    void testProductModel() {
        // Arrange
        Long id = 1L;
        String title = "ProductTitle";
        String description = "ProductDescription";
        double price = 100.0;
        int stock = 10;
        String image = "ImageURL";
        Brand brand = new Brand();
        Category category = new Category();

        // Act
        Product product = Product.builder()
                .product_id(id)
                .title(title)
                .description(description)
                .price(price)
                .stock(stock)
                .image(image)
                .brand(brand)
                .category(category)
                .build();

        // Assert
        assertNotNull(product);
        assertEquals(id, product.getProduct_id());
        assertEquals(title, product.getTitle());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(image, product.getImage());
        assertEquals(brand, product.getBrand());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testProductNoArgsConstructor() {
        // Act
        Product product = new Product();

        // Assert
        assertNotNull(product);
    }

    @Test
    void testProductAllArgsConstructor() {
        // Arrange
        Long id = 1L;
        String title = "ProductTitle";
        String description = "ProductDescription";
        double price = 100.0;
        int stock = 10;
        String image = "ImageURL";
        Brand brand = new Brand();
        Category category = new Category();

        // Act
        Product product = new Product(id, title, description, price, stock, image, brand, category, null);

        // Assert
        assertNotNull(product);
        assertEquals(id, product.getProduct_id());
        assertEquals(title, product.getTitle());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(image, product.getImage());
        assertEquals(brand, product.getBrand());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testProductSettersAndGetters() {
        // Arrange
        Product product = new Product();
        Long id = 1L;
        String title = "ProductTitle";
        String description = "ProductDescription";
        double price = 100.0;
        int stock = 10;
        String image = "ImageURL";
        Brand brand = new Brand();
        Category category = new Category();

        // Act
        product.setProduct_id(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImage(image);
        product.setBrand(brand);
        product.setCategory(category);

        // Assert
        assertEquals(id, product.getProduct_id());
        assertEquals(title, product.getTitle());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(stock, product.getStock());
        assertEquals(image, product.getImage());
        assertEquals(brand, product.getBrand());
        assertEquals(category, product.getCategory());
    }
}
