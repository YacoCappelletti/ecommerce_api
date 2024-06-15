package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductConverterTest {

    @Test
    void testToDTO() {
        // Arrange
        Brand brand = Brand.builder().id(1L).name("BrandName").description("BrandDescription").build();
        Category category = Category.builder().category_id(1L).name("CategoryName").description("CategoryDescription").build();
        Product product = Product.builder()
                .product_id(1L)
                .title("ProductTitle")
                .description("ProductDescription")
                .price(100.0)
                .stock(10)
                .brand(brand)
                .image("ImageURL")
                .category(category)
                .build();

        // Act
        ProductDTO productDTO = ProductConverter.toDTO(product);

        // Assert
        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getId());
        assertEquals("ProductTitle", productDTO.getTitle());
        assertEquals("ProductDescription", productDTO.getDescription());
        assertEquals(100.0, productDTO.getPrice());
        assertEquals(10, productDTO.getStock());
        assertEquals("ImageURL", productDTO.getImage());
        assertNotNull(productDTO.getBrand());
        assertEquals(1L, productDTO.getBrand().getId());
        assertNotNull(productDTO.getCategory());
        assertEquals(1L, productDTO.getCategory().getId());
    }

    @Test
    void testToEntity() {
        // Arrange
        BrandDTO brandDTO = BrandDTO.builder().id(1L).name("BrandName").description("BrandDescription").build();
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("CategoryName").description("CategoryDescription").build();
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .title("ProductTitle")
                .description("ProductDescription")
                .price(100.0)
                .stock(10)
                .brand(brandDTO)
                .image("ImageURL")
                .category(categoryDTO)
                .build();

        // Act
        Product product = ProductConverter.toEntity(productDTO);

        // Assert
        assertNotNull(product);
        assertEquals(1L, product.getProduct_id());
        assertEquals("ProductTitle", product.getTitle());
        assertEquals("ProductDescription", product.getDescription());
        assertEquals(100.0, product.getPrice());
        assertEquals(10, product.getStock());
        assertEquals("ImageURL", product.getImage());
        assertNotNull(product.getBrand());
        assertEquals(1L, product.getBrand().getId());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getCategory_id());
    }

    @Test
    void testToDTOList() {
        // Arrange
        Brand brand = Brand.builder().id(1L).name("BrandName").description("BrandDescription").build();
        Category category = Category.builder().category_id(1L).name("CategoryName").description("CategoryDescription").build();
        Product product1 = Product.builder()
                .product_id(1L)
                .title("ProductTitle1")
                .description("ProductDescription1")
                .price(100.0)
                .stock(10)
                .brand(brand)
                .image("ImageURL1")
                .category(category)
                .build();
        Product product2 = Product.builder()
                .product_id(2L)
                .title("ProductTitle2")
                .description("ProductDescription2")
                .price(200.0)
                .stock(20)
                .brand(brand)
                .image("ImageURL2")
                .category(category)
                .build();
        List<Product> products = Arrays.asList(product1, product2);

        // Act
        List<ProductDTO> productDTOs = ProductConverter.toDTOList(products);

        // Assert
        assertNotNull(productDTOs);
        assertEquals(2, productDTOs.size());
        assertEquals(1L, productDTOs.get(0).getId());
        assertEquals("ProductTitle1", productDTOs.get(0).getTitle());
        assertEquals(2L, productDTOs.get(1).getId());
        assertEquals("ProductTitle2", productDTOs.get(1).getTitle());
    }

    @Test
    void testToEntityList() {
        // Arrange
        BrandDTO brandDTO = BrandDTO.builder().id(1L).name("BrandName").description("BrandDescription").build();
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("CategoryName").description("CategoryDescription").build();
        ProductDTO productDTO1 = ProductDTO.builder()
                .id(1L)
                .title("ProductTitle1")
                .description("ProductDescription1")
                .price(100.0)
                .stock(10)
                .brand(brandDTO)
                .image("ImageURL1")
                .category(categoryDTO)
                .build();
        ProductDTO productDTO2 = ProductDTO.builder()
                .id(2L)
                .title("ProductTitle2")
                .description("ProductDescription2")
                .price(200.0)
                .stock(20)
                .brand(brandDTO)
                .image("ImageURL2")
                .category(categoryDTO)
                .build();
        List<ProductDTO> productDTOs = Arrays.asList(productDTO1, productDTO2);

        // Act
        List<Product> products = ProductConverter.toEntityList(productDTOs);

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getProduct_id());
        assertEquals("ProductTitle1", products.get(0).getTitle());
        assertEquals(2L, products.get(1).getProduct_id());
        assertEquals("ProductTitle2", products.get(1).getTitle());
    }
}
