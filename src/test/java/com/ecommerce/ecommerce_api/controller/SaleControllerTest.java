package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductSalesReportDTO;
import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.service.SaleService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class SaleControllerTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private SaleDTO createSaleDTO(Long id, Double totalPrice, Integer totalProducts, LocalDate date) {
        return new SaleDTO(id, totalPrice, totalProducts, date, 1L, Collections.emptyList());
    }

    @Test
    void testDownloadSalesExcel() throws IOException {
        // Arrange
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getAllSales()).thenReturn(sales);

        // Act
        ResponseEntity<ByteArrayResource> response = saleController.downloadSalesExcel();

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("attachment; filename=sales.xlsx", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(MediaType.APPLICATION_OCTET_STREAM, response.getHeaders().getContentType());
    }

    @Test
    void testListSales() {
        // Arrange
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getAllSales()).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.listSales();

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testListSalesPaginated() {
        // Arrange
        Page<SaleDTO> salesPage = new PageImpl<>(Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now())));
        when(saleService.getAllSalesPaginated(0, 10)).thenReturn(salesPage);

        // Act
        ResponseEntity<Page<SaleDTO>> response = saleController.listSalesPaginated(0, 10);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(salesPage, response.getBody());
    }

    @Test
    void testSalesByProduct() {
        // Arrange
        Long productId = 1L;
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByProduct(productId)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.salesByProduct(productId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testSalesByDay() {
        // Arrange
        LocalDate date = LocalDate.now();
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, date), createSaleDTO(2L, 200.0, 3, date));
        when(saleService.getSalesByDay(date)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.salesByDay(date);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testSalesByDateRange() {
        // Arrange
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByDateRange(startDate, endDate)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.salesByDateRange(startDate, endDate);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testSalesByCustomer() {
        // Arrange
        Long customerId = 1L;
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByCustomer(customerId)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.salesByCustomer(customerId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testDownloadBrandSalesReport() throws IOException {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        Map<String, List<ProductSalesReportDTO>> report = Collections.singletonMap(
                "Category", Arrays.asList(new ProductSalesReportDTO(1L, "Product", "Category", 1, 100.0))
        );
        when(saleService.getProductSalesReport(brandId, startDate, endDate)).thenReturn(report);

        // Act
        ResponseEntity<ByteArrayResource> response = saleController.downloadBrandSalesReport(brandId, startDate, endDate);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("attachment; filename=product_sales_report.xlsx", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(MediaType.APPLICATION_OCTET_STREAM, response.getHeaders().getContentType());
    }

    @Test
    void testDownloadSalesByBrandExcel() throws IOException {
        // Arrange
        Long brandId = 1L;
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByBrand(brandId)).thenReturn(sales);

        // Act
        ResponseEntity<ByteArrayResource> response = saleController.downloadSalesByBrandExcel(brandId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals("attachment; filename=brand_sales.xlsx", response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION));
        assertEquals(MediaType.APPLICATION_OCTET_STREAM, response.getHeaders().getContentType());
    }

    @Test
    void testGetSalesByBrand() {
        // Arrange
        Long brandId = 1L;
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByBrand(brandId)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByBrand(brandId);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    void testGetSalesByBrandAndDateRange() {
        // Arrange
        Long brandId = 1L;
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        List<SaleDTO> sales = Arrays.asList(createSaleDTO(1L, 100.0, 2, LocalDate.now()), createSaleDTO(2L, 200.0, 3, LocalDate.now()));
        when(saleService.getSalesByBrandAndDateRange(brandId, startDate, endDate)).thenReturn(sales);

        // Act
        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByBrandAndDateRange(brandId, startDate, endDate);

        // Assert
        assertEquals(OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }
}
