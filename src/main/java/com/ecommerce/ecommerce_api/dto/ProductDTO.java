package com.ecommerce.ecommerce_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String image;

    private CategoryDTO category;

    @JsonIgnore
    private List<SaleDTO> sales;
}
