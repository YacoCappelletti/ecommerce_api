package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.model.Sale;

import java.util.List;
import java.util.stream.Collectors;

public class SaleConverter {
    public static SaleDTO toDTO(Sale sale) {
        return SaleDTO.builder()
                .id(sale.getId())
                .totalPrice(sale.getTotal_price())
                .totalProducts(sale.getTotal_products())
                .date(sale.getDate())
                .customerId(sale.getCustomerId())
                .products(ProductConverter.toDTOList(sale.getProducts()))
                .build();
    }

    public static Sale toEntity(SaleDTO saleDTO) {
        return Sale.builder()
                .id(saleDTO.getId())
                .total_price(saleDTO.getTotalPrice())
                .total_products(saleDTO.getTotalProducts())
                .date(saleDTO.getDate())
                .customerId(saleDTO.getCustomerId())
                .products(ProductConverter.toEntityList(saleDTO.getProducts()))
                .build();
    }

    public static List<SaleDTO> toDTOList(List<Sale> sales) {
        return sales.stream()
                .map(SaleConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Sale> toEntityList(List<SaleDTO> saleDTOs) {
        return saleDTOs.stream()
                .map(SaleConverter::toEntity)
                .collect(Collectors.toList());
    }
}
