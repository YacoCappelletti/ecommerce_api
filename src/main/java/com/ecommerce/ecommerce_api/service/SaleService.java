package com.ecommerce.ecommerce_api.service;

import com.ecommerce.ecommerce_api.dto.ProductSalesReportDTO;
import com.ecommerce.ecommerce_api.dto.SaleDTO;
import com.ecommerce.ecommerce_api.model.Sale;
import com.ecommerce.ecommerce_api.repository.SaleRepository;
import com.ecommerce.ecommerce_api.util.SaleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<SaleDTO> getAllSales() {
        return SaleConverter.toDTOList(saleRepository.findAll());
    }

    public List<SaleDTO> getSalesByProduct(Long productId) {
        return SaleConverter.toDTOList(saleRepository.findByProductId(productId));
    }

    public List<SaleDTO> getSalesByDay(LocalDate date) {
        return SaleConverter.toDTOList(saleRepository.findByDate(date));
    }

    public List<SaleDTO> getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        return SaleConverter.toDTOList(saleRepository.findByDateBetween(startDate, endDate));
    }

    public List<SaleDTO> getSalesByCustomer(Long customerId) {
        return SaleConverter.toDTOList(saleRepository.findByCustomerId(customerId));
    }

    public Page<SaleDTO> getAllSalesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Sale> salesPage = saleRepository.findAll(pageable);
        return salesPage.map(SaleConverter::toDTO);
    }

    public Map<String, List<ProductSalesReportDTO>> getProductSalesReport(Long brandId, LocalDate startDate, LocalDate endDate) {
        List<Sale> sales = saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate);

        return sales.stream()
                .flatMap(sale -> sale.getProducts().stream()
                        .filter(product -> product.getBrand().getId().equals(brandId))
                        .map(product -> new ProductSalesReportDTO(
                                product.getProduct_id(),
                                product.getTitle(),
                                product.getCategory().getName(),
                                1, // Cada producto en una venta representa una unidad vendida
                                product.getPrice()
                        )))
                .collect(Collectors.groupingBy(
                        ProductSalesReportDTO::getCategory,
                        Collectors.toMap(
                                ProductSalesReportDTO::getProductId,
                                report -> report,
                                (report1, report2) -> {
                                    report1.setQuantitySold(report1.getQuantitySold() + report2.getQuantitySold());
                                    report1.setTotalAmountSold(report1.getTotalAmountSold() + report2.getTotalAmountSold());
                                    return report1;
                                }
                        )))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().values().stream()
                                .sorted((a, b) -> Integer.compare(b.getQuantitySold(), a.getQuantitySold()))
                                .collect(Collectors.toList())
                ));
    }


    public List<SaleDTO> getSalesByBrandAndDateRange(Long brandId, LocalDate startDate, LocalDate endDate) {
        return SaleConverter.toDTOList(saleRepository.findByBrandIdAndDateBetween(brandId, startDate, endDate));
    }
    public List<SaleDTO> getSalesByBrand(Long brandId) {
        return SaleConverter.toDTOList(saleRepository.findByBrandId(brandId));
    }
}
