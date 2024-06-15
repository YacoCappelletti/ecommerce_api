package com.ecommerce.ecommerce_api.util;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.model.Brand;

import java.util.List;
import java.util.stream.Collectors;

public class BrandConverter {
    public static BrandDTO toDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
    }

    public static Brand toEntity(BrandDTO brandDTO) {
        return Brand.builder()
                .id(brandDTO.getId())
                .name(brandDTO.getName())
                .description(brandDTO.getDescription())
                .build();
    }

    public static List<BrandDTO> toDTOList(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Brand> toEntityList(List<BrandDTO> brandDTOs) {
        return brandDTOs.stream()
                .map(BrandConverter::toEntity)
                .collect(Collectors.toList());
    }
}
