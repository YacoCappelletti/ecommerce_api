package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s JOIN s.products p WHERE p.id = :productId")
    List<Sale> findByProductId(@Param("productId") Long productId);

    List<Sale> findByDate(LocalDate date);

    List<Sale> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Sale> findByCustomerId(Long customerId);

    Page<Sale> findAll(Pageable pageable);

    @Query("SELECT s FROM Sale s JOIN s.products p WHERE p.brand.id = :brandId AND s.date BETWEEN :startDate AND :endDate")
    List<Sale> findByBrandIdAndDateBetween(@Param("brandId") Long brandId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM Sale s JOIN s.products p WHERE p.brand.id = :brandId")
    List<Sale> findByBrandId(@Param("brandId") Long brandId);
}
