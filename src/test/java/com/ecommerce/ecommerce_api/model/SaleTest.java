package com.ecommerce.ecommerce_api.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaleTest {

    @Test
    void testSaleModel() {
        // Arrange
        Long id = 1L;
        double totalPrice = 100.0;
        int totalProducts = 1;
        LocalDate date = LocalDate.now();
        Long customerId = 1L;
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Sale sale = Sale.builder()
                .id(id)
                .total_price(totalPrice)
                .total_products(totalProducts)
                .date(date)
                .customerId(customerId)
                .products(products)
                .build();

        // Assert
        assertNotNull(sale);
        assertEquals(id, sale.getId());
        assertEquals(totalPrice, sale.getTotal_price());
        assertEquals(totalProducts, sale.getTotal_products());
        assertEquals(date, sale.getDate());
        assertEquals(customerId, sale.getCustomerId());
        assertEquals(products, sale.getProducts());
    }

    @Test
    void testSaleNoArgsConstructor() {
        // Act
        Sale sale = new Sale();

        // Assert
        assertNotNull(sale);
    }

    @Test
    void testSaleAllArgsConstructor() {
        // Arrange
        Long id = 1L;
        double totalPrice = 100.0;
        int totalProducts = 1;
        LocalDate date = LocalDate.now();
        Long customerId = 1L;
        List<Product> products = Collections.singletonList(new Product());

        // Act
        Sale sale = new Sale(id, totalPrice, totalProducts, date, customerId, products);

        // Assert
        assertNotNull(sale);
        assertEquals(id, sale.getId());
        assertEquals(totalPrice, sale.getTotal_price());
        assertEquals(totalProducts, sale.getTotal_products());
        assertEquals(date, sale.getDate());
        assertEquals(customerId, sale.getCustomerId());
        assertEquals(products, sale.getProducts());
    }

    @Test
    void testSaleSettersAndGetters() {
        // Arrange
        Sale sale = new Sale();
        Long id = 1L;
        double totalPrice = 100.0;
        int totalProducts = 1;
        LocalDate date = LocalDate.now();
        Long customerId = 1L;
        List<Product> products = Collections.singletonList(new Product());

        // Act
        sale.setId(id);
        sale.setTotal_price(totalPrice);
        sale.setTotal_products(totalProducts);
        sale.setDate(date);
        sale.setCustomerId(customerId);
        sale.setProducts(products);

        // Assert
        assertEquals(id, sale.getId());
        assertEquals(totalPrice, sale.getTotal_price());
        assertEquals(totalProducts, sale.getTotal_products());
        assertEquals(date, sale.getDate());
        assertEquals(customerId, sale.getCustomerId());
        assertEquals(products, sale.getProducts());
    }
}
