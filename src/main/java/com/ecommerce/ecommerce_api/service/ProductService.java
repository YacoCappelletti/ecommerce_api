package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.repository.ProductRepository;
import com.ecommerce.ecommerce_api.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return ProductConverter.toDTOList(productRepository.findAll());
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return ProductConverter.toDTOList(productRepository.findByCategoryId(categoryId));
    }

    public List<ProductDTO> getProductsWithLowStock() {
        return ProductConverter.toDTOList(productRepository.findByStockLessThan(5));
    }

    public List<ProductDTO> getProductsByBrand(Long brandId) {
        return ProductConverter.toDTOList(productRepository.findByBrand_Id(brandId));
    }

    public List<ProductDTO> getProductsByName(String name) {
        return ProductConverter.toDTOList(productRepository.findByTitleContainingIgnoreCase(name));
    }

    public List<ProductDTO> searchProducts(String query) {
        return ProductConverter.toDTOList(productRepository.findByTitleContainingIgnoreCase(query));
    }
}
