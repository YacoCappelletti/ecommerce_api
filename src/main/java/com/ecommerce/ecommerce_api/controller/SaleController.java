package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.ProductSalesReportDTO;
import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/sales")
@Tag(name = "Sale Controller", description = "Controller for managing sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/excel")
    @Operation(summary = "Download sales as Excel", description = "Download the list of all sales as an Excel file")
    public ResponseEntity<ByteArrayResource> downloadSalesExcel() throws IOException {
        List<SaleDTO> sales = saleService.getAllSales();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sales");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Sale ID");
        headerRow.createCell(1).setCellValue("Total Price");
        headerRow.createCell(2).setCellValue("Total Products");
        headerRow.createCell(3).setCellValue("Date");

        int rowNum = 1;
        for (SaleDTO sale : sales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sale.getId());
            row.createCell(1).setCellValue(sale.getTotalPrice());
            row.createCell(2).setCellValue(sale.getTotalProducts());
            row.createCell(3).setCellValue(sale.getDate().toString());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        ByteArrayResource resource = new ByteArrayResource(bos.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sales.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping
    @Operation(summary = "List all sales", description = "Fetch the list of all sales")
    public ResponseEntity<List<SaleDTO>> listSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/paginated")
    @Operation(summary = "List sales with pagination", description = "Fetch a paginated list of sales")
    public ResponseEntity<Page<SaleDTO>> listSalesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<SaleDTO> sales = saleService.getAllSalesPaginated(page, size);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get sales by product", description = "Fetch the list of sales for a specific product")
    public ResponseEntity<List<SaleDTO>> salesByProduct(@PathVariable Long productId) {
        List<SaleDTO> sales = saleService.getSalesByProduct(productId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/day/{date}")
    @Operation(summary = "Get sales by day", description = "Fetch the list of sales for a specific day")
    public ResponseEntity<List<SaleDTO>> salesByDay(@PathVariable LocalDate date) {
        List<SaleDTO> sales = saleService.getSalesByDay(date);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/range")
    @Operation(summary = "Get sales by date range", description = "Fetch the list of sales within a specific date range")
    public ResponseEntity<List<SaleDTO>> salesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<SaleDTO> sales = saleService.getSalesByDateRange(startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get sales by customer", description = "Fetch the list of sales for a specific customer")
    public ResponseEntity<List<SaleDTO>> salesByCustomer(@PathVariable Long customerId) {
        List<SaleDTO> sales = saleService.getSalesByCustomer(customerId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/brand/{brandId}/report")
    @Operation(summary = "Download sales report by brand", description = "Download a sales report for a specific brand within a date range as an Excel file")
    public ResponseEntity<ByteArrayResource> downloadBrandSalesReport(
            @PathVariable Long brandId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) throws IOException {

        Map<String, List<ProductSalesReportDTO>> report = saleService.getProductSalesReport(brandId, startDate, endDate);

        Workbook workbook = new XSSFWorkbook();
        for (Map.Entry<String, List<ProductSalesReportDTO>> entry : report.entrySet()) {
            String categoryName = entry.getKey();
            List<ProductSalesReportDTO> products = entry.getValue();

            Sheet sheet = workbook.createSheet(categoryName);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Product ID");
            headerRow.createCell(1).setCellValue("Product Name");
            headerRow.createCell(2).setCellValue("Quantity Sold");
            headerRow.createCell(3).setCellValue("Total Amount Sold");

            int rowNum = 1;
            for (ProductSalesReportDTO item : products) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.getProductId());
                row.createCell(1).setCellValue(item.getProductName());
                row.createCell(2).setCellValue(item.getQuantitySold());
                row.createCell(3).setCellValue(item.getTotalAmountSold());
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        ByteArrayResource resource = new ByteArrayResource(bos.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=product_sales_report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/brand/{brandId}/excel")
    @Operation(summary = "Download sales by brand as Excel", description = "Download the list of sales for a specific brand as an Excel file")
    public ResponseEntity<ByteArrayResource> downloadSalesByBrandExcel(@PathVariable Long brandId) throws IOException {
        List<SaleDTO> sales = saleService.getSalesByBrand(brandId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Brand Sales");

        // Create header row with styles
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Sale ID");
        headerRow.createCell(1).setCellValue("Total Price");
        headerRow.createCell(2).setCellValue("Total Products");
        headerRow.createCell(3).setCellValue("Date");

        // Populate data rows
        int rowNum = 1;
        for (SaleDTO sale : sales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sale.getId());
            row.createCell(1).setCellValue(sale.getTotalPrice());
            row.createCell(2).setCellValue(sale.getTotalProducts());
            row.createCell(3).setCellValue(sale.getDate().toString());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        ByteArrayResource resource = new ByteArrayResource(bos.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=brand_sales.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Get sales by brand", description = "Fetch the list of sales for a specific brand")
    public ResponseEntity<List<SaleDTO>> getSalesByBrand(@PathVariable Long brandId) {
        List<SaleDTO> sales = saleService.getSalesByBrand(brandId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/brand/{brandId}/range")
    @Operation(summary = "Get sales by brand and date range", description = "Fetch the list of sales for a specific brand within a date range")
    public ResponseEntity<List<SaleDTO>> getSalesByBrandAndDateRange(
            @PathVariable Long brandId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<SaleDTO> sales = saleService.getSalesByBrandAndDateRange(brandId, startDate, endDate);
        return ResponseEntity.ok(sales);
    }
}
