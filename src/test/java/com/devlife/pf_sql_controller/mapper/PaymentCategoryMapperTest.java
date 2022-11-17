package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.PaymentCategoryDto;
import com.devlife.pf_sql_controller.entity.PaymentCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentCategory mapper tests")
class PaymentCategoryMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    PaymentCategoryMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО PaymentCategory в Entity")
    void convertToEntityTest_OK() {
        PaymentCategory referencePaymentCategoryEntity = new PaymentCategory();
        referencePaymentCategoryEntity.setId(1L);
        referencePaymentCategoryEntity.setName("Test");
        PaymentCategoryDto paymentCategoryDto = PaymentCategoryDto.builder()
                .id(1L)
                .name("Test")
                .build();

        PaymentCategory paymentCategory = mapper.convertToEntity(paymentCategoryDto);
        assertEquals(referencePaymentCategoryEntity,paymentCategory);
    }

    @Test
    @DisplayName("Конвертируем Entity PaymentCategory в ДТО")
    void convertToDtoTest_OK() {
        PaymentCategoryDto referencePaymentCategoryDto = PaymentCategoryDto.builder()
                .id(1L)
                .name("Test")
                .build();
        PaymentCategory paymentCategoryEntity = new PaymentCategory();
        paymentCategoryEntity.setId(1L);
        paymentCategoryEntity.setName("Test");
        PaymentCategoryDto paymentCategoryDto = mapper.convertToDto(paymentCategoryEntity);
        assertEquals(referencePaymentCategoryDto,paymentCategoryDto);
    }
}