package com.ecommerce.ecommerce_api.repository;

import com.ecommerce.ecommerce_api.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
