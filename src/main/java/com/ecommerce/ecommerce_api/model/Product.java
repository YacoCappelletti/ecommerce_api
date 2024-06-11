package com.ecommerce.ecommerce_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String title;
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String image;

    @ManyToMany(mappedBy = "products")
    private List<Sale> sales;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
