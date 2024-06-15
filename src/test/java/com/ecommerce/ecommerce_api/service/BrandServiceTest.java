package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import com.ecommerce.ecommerce_api.repository.BrandRepository;
import com.ecommerce.ecommerce_api.repository.ProductRepository;
import com.ecommerce.ecommerce_api.util.BrandConverter;
import com.ecommerce.ecommerce_api.util.ProductConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Brand createBrand(Long brandId) {
        return new Brand(brandId, "BrandName", "BrandDescription", null);
    }

    private Product createProduct(Long productId, Long brandId, Long categoryId) {
        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Category category = new Category(categoryId, "CategoryName", "CategoryDescription", null);
        return new Product(productId, "ProductName", "ProductDescription", 100.0, 1, "imageUrl", brand, category, null);
    }

    @Test
    void testGetAllBrands() {
        // Arrange
        List<Brand> brands = Arrays.asList(createBrand(1L), createBrand(2L));
        when(brandRepository.findAll()).thenReturn(brands);

        // Act
        List<BrandDTO> result = brandService.getAllBrands();

        // Assert
        assertEquals(BrandConverter.toDTOList(brands), result);
    }

    @Test
    void testGetBrandById() {
        // Arrange
        Long brandId = 1L;
        Brand brand = createBrand(brandId);
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));

        // Act
        BrandDTO result = brandService.getBrandById(brandId);

        // Assert
        assertEquals(BrandConverter.toDTO(brand), result);
    }

    @Test
    void testGetProductsByBrand() {
        // Arrange
        Long brandId = 1L;
        List<Product> products = Arrays.asList(createProduct(1L, brandId, 1L));
        when(productRepository.findByBrand_Id(brandId)).thenReturn(products);

        // Act
        List<ProductDTO> result = brandService.getProductsByBrand(brandId);

        // Assert
        assertEquals(ProductConverter.toDTOList(products), result);
    }
}
