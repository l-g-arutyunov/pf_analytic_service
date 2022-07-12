package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.PaymentCategoryDto;
import com.devlife.pf_sql_controller.entity.PaymentCategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentCategoryMapper {
    private final ModelMapper mapper;

    public PaymentCategoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PaymentCategory convertToEntity(PaymentCategoryDto paymentCategoryDto) {
        return mapper.map(paymentCategoryDto, PaymentCategory.class);
    }

    public PaymentCategoryDto convertToDto(PaymentCategory paymentCategoryEntity) {
        return mapper.map(paymentCategoryEntity, PaymentCategoryDto.class);
    }
}
