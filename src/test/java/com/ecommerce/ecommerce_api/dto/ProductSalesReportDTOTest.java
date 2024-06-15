package com.ecommerce.ecommerce_api.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductSalesReportDTOTest {

    @Test
    void testProductSalesReportDTO() {
        // Arrange
        Long productId = 1L;
        String productName = "ProductName";
        String category = "CategoryName";
        int quantitySold = 10;
        double totalAmountSold = 1000.0;

        // Act
        ProductSalesReportDTO reportDTO = ProductSalesReportDTO.builder()
                .productId(productId)
                .productName(productName)
                .category(category)
                .quantitySold(quantitySold)
                .totalAmountSold(totalAmountSold)
                .build();

        // Assert
        assertNotNull(reportDTO);
        assertEquals(productId, reportDTO.getProductId());
        assertEquals(productName, reportDTO.getProductName());
        assertEquals(category, reportDTO.getCategory());
        assertEquals(quantitySold, reportDTO.getQuantitySold());
        assertEquals(totalAmountSold, reportDTO.getTotalAmountSold());
    }
}
