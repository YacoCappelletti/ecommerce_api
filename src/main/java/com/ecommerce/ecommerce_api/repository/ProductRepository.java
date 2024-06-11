package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Product> findByStockLessThan(int stock);
    List<Product> findByBrand(String brand);


    @Query("SELECT p FROM Product p WHERE LOWER(TRIM(p.title)) LIKE LOWER(CONCAT('%', TRIM(:title), '%'))")
    List<Product> findByTitleContainingIgnoreCase(@Param("title") String title);
}
