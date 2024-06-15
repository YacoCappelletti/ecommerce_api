package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.repository.BrandRepository;
import com.ecommerce.ecommerce_api.repository.ProductRepository;
import com.ecommerce.ecommerce_api.util.BrandConverter;
import com.ecommerce.ecommerce_api.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<BrandDTO> getAllBrands() {
        return BrandConverter.toDTOList(brandRepository.findAll());
    }

    public BrandDTO getBrandById(Long id) {
        return BrandConverter.toDTO(brandRepository.findById(id).orElse(null));
    }

    public List<ProductDTO> getProductsByBrand(Long brandId) {
        return ProductConverter.toDTOList(productRepository.findByBrand_Id(brandId));
    }
}
