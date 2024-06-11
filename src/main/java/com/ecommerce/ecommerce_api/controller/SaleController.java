package com.ecommerce.ecommerce_api.controller;

import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.service.SaleService;
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

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/excel")
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
    public ResponseEntity<List<SaleDTO>> listSales() {
        List<SaleDTO> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<SaleDTO>> listSalesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<SaleDTO> sales = saleService.getAllSalesPaginated(page, size);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SaleDTO>> salesByProduct(@PathVariable Long productId) {
        List<SaleDTO> sales = saleService.getSalesByProduct(productId);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/day/{date}")
    public ResponseEntity<List<SaleDTO>> salesByDay(@PathVariable LocalDate date) {
        List<SaleDTO> sales = saleService.getSalesByDay(date);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/range")
    public ResponseEntity<List<SaleDTO>> salesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<SaleDTO> sales = saleService.getSalesByDateRange(startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleDTO>> salesByCustomer(@PathVariable Long customerId) {
        List<SaleDTO> sales = saleService.getSalesByCustomer(customerId);
        return ResponseEntity.ok(sales);
    }
}
