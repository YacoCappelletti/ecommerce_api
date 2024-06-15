package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.service.BrandService;
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

class BrandControllerTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListBrands() {
        // Arrange
        List<BrandDTO> brands = Arrays.asList(new BrandDTO(), new BrandDTO());
        when(brandService.getAllBrands()).thenReturn(brands);

        // Act
        ResponseEntity<List<BrandDTO>> response = brandController.listBrands();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brands, response.getBody());
    }

    @Test
    void testGetBrand() {
        // Arrange
        Long brandId = 1L;
        BrandDTO brand = new BrandDTO();
        when(brandService.getBrandById(brandId)).thenReturn(brand);

        // Act
        ResponseEntity<BrandDTO> response = brandController.getBrand(brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brand, response.getBody());
    }

    @Test
    void testGetProductsByBrand() {
        // Arrange
        Long brandId = 1L;
        List<ProductDTO> products = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(brandService.getProductsByBrand(brandId)).thenReturn(products);

        // Act
        ResponseEntity<List<ProductDTO>> response = brandController.getProductsByBrand(brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}
