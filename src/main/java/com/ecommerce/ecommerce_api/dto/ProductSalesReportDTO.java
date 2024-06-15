package com.ecommerce.ecommerce_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSalesReportDTO {
    private Long productId;
    private String productName;
    private String category;
    private int quantitySold;
    private double totalAmountSold;
}
