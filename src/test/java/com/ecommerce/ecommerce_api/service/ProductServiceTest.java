package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import com.ecommerce.ecommerce_api.repository.ProductRepository;
import com.ecommerce.ecommerce_api.util.ProductConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Product createProduct(Long productId, Long brandId, Long categoryId) {
        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Category category = new Category(categoryId, "CategoryName", "CategoryDescription", null);
        return new Product(productId, "ProductName", "ProductDescription", 100.0, 10, "imageUrl", brand, category, null);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(createProduct(1L, 1L, 1L), createProduct(2L, 1L, 1L));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }

    @Test
    void testGetProductsByCategory() {
        // Arrange
        Long categoryId = 1L;
        List<Product> products = Arrays.asList(createProduct(1L, 1L, categoryId));
        when(productRepository.findByCategoryId(categoryId)).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getProductsByCategory(categoryId);

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }

    @Test
    void testGetProductsWithLowStock() {
        // Arrange
        List<Product> products = Arrays.asList(createProduct(1L, 1L, 1L));
        products.get(0).setStock(3); // Set stock to less than 5
        when(productRepository.findByStockLessThan(5)).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getProductsWithLowStock();

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }

    @Test
    void testGetProductsByBrand() {
        // Arrange
        Long brandId = 1L;
        List<Product> products = Arrays.asList(createProduct(1L, brandId, 1L));
        when(productRepository.findByBrand_Id(brandId)).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getProductsByBrand(brandId);

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }

    @Test
    void testGetProductsByName() {
        // Arrange
        String name = "ProductName";
        List<Product> products = Arrays.asList(createProduct(1L, 1L, 1L));
        when(productRepository.findByTitleContainingIgnoreCase(name)).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.getProductsByName(name);

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }

    @Test
    void testSearchProducts() {
        // Arrange
        String query = "ProductName";
        List<Product> products = Arrays.asList(createProduct(1L, 1L, 1L));
        when(productRepository.findByTitleContainingIgnoreCase(query)).thenReturn(products);

        // Act
        List<ProductDTO> result = productService.searchProducts(query);

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }
}
