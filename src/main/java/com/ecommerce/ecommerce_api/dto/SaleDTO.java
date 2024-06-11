package com.ecommerce.ecommerce_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private double totalPrice;
    private int totalProducts;
    private LocalDate date;
    private Long customerId;
    private List<ProductDTO> products;
}
