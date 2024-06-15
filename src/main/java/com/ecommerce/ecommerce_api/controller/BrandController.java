package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.BrandDTO;
import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@Tag(name = "Brand Controller", description = "Controller for managing brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    @Operation(summary = "List all brands", description = "Fetch all the brands available in the store")
    public ResponseEntity<List<BrandDTO>> listBrands() {
        List<BrandDTO> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Fetch a brand by its ID")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long id) {
        BrandDTO brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Get products by brand", description = "Fetch all products associated with a specific brand")
    public ResponseEntity<List<ProductDTO>> getProductsByBrand(@PathVariable Long id) {
        List<ProductDTO> products = brandService.getProductsByBrand(id);
        return ResponseEntity.ok(products);
    }
}
