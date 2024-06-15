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

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")
    List<Product> findByBrand_Id(@Param("brandId") Long brandId);

    @Query("SELECT p FROM Product p WHERE LOWER(TRIM(p.title)) LIKE LOWER(CONCAT('%', TRIM(:title), '%'))")
    List<Product> findByTitleContainingIgnoreCase(@Param("title") String title);
}
