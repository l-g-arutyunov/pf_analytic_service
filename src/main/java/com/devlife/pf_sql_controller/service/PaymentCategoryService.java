package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.PaymentCategoryDto;
import com.devlife.pf_sql_controller.entity.PaymentCategory;
import com.devlife.pf_sql_controller.mapper.PaymentCategoryMapper;
import com.devlife.pf_sql_controller.repository.PaymentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentCategoryService {
    private final PaymentCategoryRepository paymentCategoryRepository;
    private final PaymentCategoryMapper mapper;

    public Long addPaymentCategory(PaymentCategoryDto paymentCategory) {
        PaymentCategory savePaymentCategory = paymentCategoryRepository.save(mapper.convertToEntity(paymentCategory));
        if (savePaymentCategory != null) {
            return savePaymentCategory.getId();
        }
        return null;
    }

    public PaymentCategoryDto getPaymentCategory(Long id) {
        PaymentCategory paymentCategory = paymentCategoryRepository.getById(id);
        return mapper.convertToDto(paymentCategory);
    }

    public List<PaymentCategoryDto> getAllPaymentCategories() {
        List<PaymentCategory> paymentCategoriesList = paymentCategoryRepository.findAll();
        return paymentCategoriesList.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Boolean deletePaymentCategoryById(Long id) {
        paymentCategoryRepository.deleteById(id);
        return !paymentCategoryRepository.existsById(id);
    }
}
