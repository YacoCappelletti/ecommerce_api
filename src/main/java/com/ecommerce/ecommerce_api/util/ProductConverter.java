package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getProduct_id())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .brand(BrandConverter.toDTO(product.getBrand()))  // Actualiza esto
                .image(product.getImage())
                .category(CategoryConverter.toDTO(product.getCategory()))
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .product_id(productDTO.getId())
                .title(productDTO.getTitle())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .brand(BrandConverter.toEntity(productDTO.getBrand()))
                .image(productDTO.getImage())
                .category(CategoryConverter.toEntity(productDTO.getCategory()))
                .build();
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream()
                .map(ProductConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Product> toEntityList(List<ProductDTO> productDTOs) {
        return productDTOs.stream()
                .map(ProductConverter::toEntity)
                .collect(Collectors.toList());
    }
}
