package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.CategoryDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import com.ecommerce.ecommerce_api.model.Sale;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaleConverterTest {

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
        Sale sale = Sale.builder()
                .id(1L)
                .total_price(100.0)
                .total_products(1)
                .date(LocalDate.now())
                .customerId(1L)
                .products(Collections.singletonList(product))
                .build();

        // Act
        SaleDTO saleDTO = SaleConverter.toDTO(sale);

        // Assert
        assertNotNull(saleDTO);
        assertEquals(1L, saleDTO.getId());
        assertEquals(100.0, saleDTO.getTotalPrice());
        assertEquals(1, saleDTO.getTotalProducts());
        assertEquals(1L, saleDTO.getCustomerId());
        assertNotNull(saleDTO.getProducts());
        assertEquals(1, saleDTO.getProducts().size());
        assertEquals(1L, saleDTO.getProducts().get(0).getId());
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
        SaleDTO saleDTO = SaleDTO.builder()
                .id(1L)
                .totalPrice(100.0)
                .totalProducts(1)
                .date(LocalDate.now())
                .customerId(1L)
                .products(Collections.singletonList(productDTO))
                .build();

        // Act
        Sale sale = SaleConverter.toEntity(saleDTO);

        // Assert
        assertNotNull(sale);
        assertEquals(1L, sale.getId());
        assertEquals(100.0, sale.getTotal_price());
        assertEquals(1, sale.getTotal_products());
        assertEquals(1L, sale.getCustomerId());
        assertNotNull(sale.getProducts());
        assertEquals(1, sale.getProducts().size());
        assertEquals(1L, sale.getProducts().get(0).getProduct_id());
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
        Sale sale1 = Sale.builder()
                .id(1L)
                .total_price(100.0)
                .total_products(1)
                .date(LocalDate.now())
                .customerId(1L)
                .products(Collections.singletonList(product1))
                .build();
        List<Sale> sales = Collections.singletonList(sale1);

        // Act
        List<SaleDTO> saleDTOs = SaleConverter.toDTOList(sales);

        // Assert
        assertNotNull(saleDTOs);
        assertEquals(1, saleDTOs.size());
        assertEquals(1L, saleDTOs.get(0).getId());
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
        SaleDTO saleDTO1 = SaleDTO.builder()
                .id(1L)
                .totalPrice(100.0)
                .totalProducts(1)
                .date(LocalDate.now())
                .customerId(1L)
                .products(Collections.singletonList(productDTO1))
                .build();
        List<SaleDTO> saleDTOs = Collections.singletonList(saleDTO1);

        // Act
        List<Sale> sales = SaleConverter.toEntityList(saleDTOs);

        // Assert
        assertNotNull(sales);
        assertEquals(1, sales.size());
        assertEquals(1L, sales.get(0).getId());
    }
}
