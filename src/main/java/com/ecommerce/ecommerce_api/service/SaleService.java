package com.ecommerce.ecommerce_api.service;

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
}
