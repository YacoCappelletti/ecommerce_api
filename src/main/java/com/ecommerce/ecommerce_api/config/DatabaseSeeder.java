package com.ecommerce.ecommerce_api.config;

import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import com.ecommerce.ecommerce_api.model.Sale;
import com.ecommerce.ecommerce_api.repository.CategoryRepository;
import com.ecommerce.ecommerce_api.repository.ProductRepository;
import com.ecommerce.ecommerce_api.repository.SaleRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/*
@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository,
                                   ProductRepository productRepository,
                                   SaleRepository saleRepository) {
        return args -> {
            Faker faker = new Faker(new Locale("en-US"));  // Utilizar configuración regional en-US
            Random random = new Random();

            // Crear categorías
            List<Category> categories = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                Category category = new Category(null, faker.commerce().department(), faker.lorem().sentence(), null);
                categories.add(category);
            }
            categoryRepository.saveAll(categories);

            // Crear productos
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Category category = categories.get(random.nextInt(categories.size()));
                Product product = new Product(
                        null,
                        faker.commerce().productName(),
                        faker.lorem().words(7).toString(),
                        Double.parseDouble(faker.commerce().price().replace(",", ".")),  // Reemplazar coma por punto
                        faker.number().numberBetween(1, 100),
                        faker.company().name(),
                        faker.internet().image(),
                        null,
                        category
                );
                products.add(product);
            }
            productRepository.saveAll(products);

            // Crear ventas
            List<Sale> sales = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                List<Product> saleProducts = new ArrayList<>();
                int numProducts = faker.number().numberBetween(1, 5);
                for (int j = 0; j < numProducts; j++) {
                    saleProducts.add(products.get(random.nextInt(products.size())));
                }
                Sale sale = new Sale(
                        null,
                        saleProducts.stream().mapToDouble(Product::getPrice).sum(),
                        saleProducts.size(),
                        LocalDate.now().minusDays(faker.number().numberBetween(0, 30)),
                        faker.number().numberBetween(1L,500L),
                        saleProducts
                );
                sales.add(sale);
            }
            saleRepository.saveAll(sales);
        };
    }
}
*/