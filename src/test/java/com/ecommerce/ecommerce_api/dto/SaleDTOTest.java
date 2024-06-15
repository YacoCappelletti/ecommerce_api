package com.ecommerce.ecommerce_api.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaleDTOTest {

    @Test
    void testSaleDTO() {
        // Arrange
        Long id = 1L;
        double totalPrice = 100.0;
        int totalProducts = 1;
        LocalDate date = LocalDate.now();
        Long customerId = 1L;
        List<ProductDTO> products = Collections.singletonList(
                ProductDTO.builder().id(1L).title("ProductTitle").description("ProductDescription").price(100.0).stock(10).image("ImageURL").build()
        );

        // Act
        SaleDTO saleDTO = SaleDTO.builder()
                .id(id)
                .totalPrice(totalPrice)
                .totalProducts(totalProducts)
                .date(date)
                .customerId(customerId)
                .products(products)
                .build();

        // Assert
        assertNotNull(saleDTO);
        assertEquals(id, saleDTO.getId());
        assertEquals(totalPrice, saleDTO.getTotalPrice());
        assertEquals(totalProducts, saleDTO.getTotalProducts());
        assertEquals(date, saleDTO.getDate());
        assertEquals(customerId, saleDTO.getCustomerId());
        assertNotNull(saleDTO.getProducts());
        assertEquals(1, saleDTO.getProducts().size());
        assertEquals(1L, saleDTO.getProducts().get(0).getId());
    }
}
