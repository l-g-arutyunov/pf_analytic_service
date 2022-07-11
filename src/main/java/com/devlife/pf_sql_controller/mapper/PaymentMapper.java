package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.PaymentDto;
import com.devlife.pf_sql_controller.entity.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    private final ModelMapper mapper;

    public PaymentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Payment convertToEntity(PaymentDto paymentDto) {
        return mapper.map(paymentDto, Payment.class);
    }

    public PaymentDto convertToDto(Payment paymentEntity) {
        return mapper.map(paymentEntity, PaymentDto.class);
    }
}
