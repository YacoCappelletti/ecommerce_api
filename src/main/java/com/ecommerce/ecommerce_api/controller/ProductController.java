package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductDTO;
import com.ecommerce.ecommerce_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller", description = "Controller for managing products")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search for products based on a query string")

    public ResponseEntity<List<ProductDTO>> searchProduct(@RequestParam String query) {
        List<ProductDTO> products = productService.searchProducts(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Fetch all the products available in the store")

    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get products by category", description = "Fetch all the products belonging to a specific category")
    public ResponseEntity<List<ProductDTO>> productsByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/stock/low")
    @Operation(summary = "Get products with low stock", description = "Fetch all the products that have less than 5 units in stock")
    public ResponseEntity<List<ProductDTO>> productsWithLowStock() {
        List<ProductDTO> products = productService.getProductsWithLowStock();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Get products by brand", description = "Fetch all the products belonging to a specific brand")
    public ResponseEntity<List<ProductDTO>> productsByBrand(@PathVariable Long brandId) {
        List<ProductDTO> products = productService.getProductsByBrand(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get products by name", description = "Fetch all the products with a specific name")
    public ResponseEntity<List<ProductDTO>> productsByName(@PathVariable String name) {
        List<ProductDTO> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }
}
