package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ProductSalesReportDTO;
import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.model.Brand;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Product;
import com.ecommerce.ecommerce_api.model.Sale;
import com.ecommerce.ecommerce_api.repository.SaleRepository;
import com.ecommerce.ecommerce_api.util.SaleConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Sale createSale(Long saleId, Long brandId, Long categoryId) {
        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Category category = new Category(categoryId, "CategoryName", "CategoryDescription", null);
        Product product = new Product(1L, "ProductName", "ProductDescription", 100.0, 1, "imageUrl", brand, category, null);
        Sale sale = new Sale(saleId, 100.0, 1, LocalDate.now(), 1L, Collections.singletonList(product));
        return sale;
    }

    @Test
    void testGetAllSales() {
        // Arrange
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L), createSale(2L, 1L, 1L));
        when(saleRepository.findAll()).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getAllSales();

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByProduct() {
        // Arrange
        Long productId = 1L;
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L));
        when(saleRepository.findByProductId(productId)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByProduct(productId);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByDay() {
        // Arrange
        LocalDate date = LocalDate.now();
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L));
        when(saleRepository.findByDate(date)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByDay(date);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByDateRange() {
        // Arrange
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L));
        when(saleRepository.findByDateBetween(startDate, endDate)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByDateRange(startDate, endDate);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByCustomer() {
        // Arrange
        Long customerId = 1L;
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L));
        when(saleRepository.findByCustomerId(customerId)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByCustomer(customerId);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByBrand() {
        // Arrange
        Long brandId = 1L;
        List<Sale> sales = Arrays.asList(createSale(1L, brandId, 1L));
        when(saleRepository.findByBrandId(brandId)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByBrand(brandId);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetSalesByBrandAndDateRange() {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        List<Sale> sales = Arrays.asList(createSale(1L, brandId, 1L));
        when(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate)).thenReturn(sales);

        // Act
        List<SaleDTO> result = saleService.getSalesByBrandAndDateRange(brandId, startDate, endDate);

        // Assert
        assertEquals(SaleConverter.toDTOList(sales), result);
    }

    @Test
    void testGetAllSalesPaginated() {
        // Arrange
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<Sale> sales = Arrays.asList(createSale(1L, 1L, 1L));
        Page<Sale> salesPage = new PageImpl<>(sales, pageable, sales.size());
        when(saleRepository.findAll(pageable)).thenReturn(salesPage);

        // Act
        Page<SaleDTO> result = saleService.getAllSalesPaginated(page, size);

        // Assert
        assertEquals(salesPage.map(SaleConverter::toDTO), result);
    }

    @Test
    void testGetProductSalesReport() {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        Sale sale = createSale(1L, 1L, 1L);
        when(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate)).thenReturn(Collections.singletonList(sale));

        // Act
        Map<String, List<ProductSalesReportDTO>> result = saleService.getProductSalesReport(brandId, startDate, endDate);

        // Assert
        ProductSalesReportDTO expectedReport = new ProductSalesReportDTO(1L, "ProductName", "CategoryName", 1, 100.0);
        Map<String, List<ProductSalesReportDTO>> expectedMap = Collections.singletonMap(
                "CategoryName", Collections.singletonList(expectedReport)
        );
        assertEquals(expectedMap, result);
    }
    @Test
    void testGetProductSalesReport_multipleProductsInSameSale() {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();

        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Category category = new Category(1L, "CategoryName", "CategoryDescription", null);
        Product product1 = new Product(1L, "ProductName1", "ProductDescription1", 100.0, 1, "imageUrl", brand, category, null);
        Product product2 = new Product(2L, "ProductName2", "ProductDescription2", 200.0, 1, "imageUrl", brand, category, null);
        Sale sale = new Sale(1L, 300.0, 2, LocalDate.now(), 1L, Arrays.asList(product1, product2));

        when(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate)).thenReturn(Collections.singletonList(sale));

        // Act
        Map<String, List<ProductSalesReportDTO>> result = saleService.getProductSalesReport(brandId, startDate, endDate);

        // Assert
        ProductSalesReportDTO report1 = new ProductSalesReportDTO(1L, "ProductName1", "CategoryName", 1, 100.0);
        ProductSalesReportDTO report2 = new ProductSalesReportDTO(2L, "ProductName2", "CategoryName", 1, 200.0);
        Map<String, List<ProductSalesReportDTO>> expectedMap = Collections.singletonMap(
                "CategoryName", Arrays.asList(report1, report2)
        );
        assertEquals(expectedMap, result);
    }

    @Test
    void testGetProductSalesReport_differentBrands() {
        // Arrange
        Long brandId = 1L;
        Long otherBrandId = 2L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();

        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Brand otherBrand = new Brand(otherBrandId, "OtherBrandName", "OtherBrandDescription", null);
        Category category = new Category(1L, "CategoryName", "CategoryDescription", null);
        Product product1 = new Product(1L, "ProductName1", "ProductDescription1", 100.0, 1, "imageUrl", brand, category, null);
        Product product2 = new Product(2L, "ProductName2", "ProductDescription2", 200.0, 1, "imageUrl", otherBrand, category, null);
        Sale sale = new Sale(1L, 300.0, 2, LocalDate.now(), 1L, Arrays.asList(product1, product2));

        when(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate)).thenReturn(Collections.singletonList(sale));

        // Act
        Map<String, List<ProductSalesReportDTO>> result = saleService.getProductSalesReport(brandId, startDate, endDate);

        // Assert
        ProductSalesReportDTO report1 = new ProductSalesReportDTO(1L, "ProductName1", "CategoryName", 1, 100.0);
        Map<String, List<ProductSalesReportDTO>> expectedMap = Collections.singletonMap(
                "CategoryName", Collections.singletonList(report1)
        );
        assertEquals(expectedMap, result);
    }
    @Test
    void testGetProductSalesReport_multipleSalesSameProduct() {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();

        Brand brand = new Brand(brandId, "BrandName", "BrandDescription", null);
        Category category = new Category(1L, "CategoryName", "CategoryDescription", null);
        Product product1 = new Product(1L, "ProductName1", "ProductDescription1", 100.0, 1, "imageUrl", brand, category, null);

        Sale sale1 = new Sale(1L, 100.0, 1, LocalDate.now(), 1L, Collections.singletonList(product1));
        Sale sale2 = new Sale(2L, 100.0, 1, LocalDate.now(), 1L, Collections.singletonList(product1));

        when(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate)).thenReturn(Arrays.asList(sale1, sale2));

        // Act
        Map<String, List<ProductSalesReportDTO>> result = saleService.getProductSalesReport(brandId, startDate, endDate);

        // Assert
        ProductSalesReportDTO expectedReport = new ProductSalesReportDTO(1L, "ProductName1", "CategoryName", 2, 200.0);
        Map<String, List<ProductSalesReportDTO>> expectedMap = Collections.singletonMap(
                "CategoryName", Collections.singletonList(expectedReport)
        );
        assertEquals(expectedMap, result);
    }

}
