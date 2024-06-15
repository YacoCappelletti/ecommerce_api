package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchProduct() {
        // Arrange
        String query = "test";
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.searchProducts(query)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.searchProduct(query);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testListProducts() {
        // Arrange
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getAllProducts()).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.listProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testProductsByCategory() {
        // Arrange
        Long categoryId = 1L;
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getProductsByCategory(categoryId)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productsByCategory(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testProductsWithLowStock() {
        // Arrange
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getProductsWithLowStock()).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productsWithLowStock();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testProductsByBrand() {
        // Arrange
        Long brandId = 1L;
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getProductsByBrand(brandId)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productsByBrand(brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testProductsByName() {
        // Arrange
        String name = "test";
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productService.getProductsByName(name)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.productsByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}
