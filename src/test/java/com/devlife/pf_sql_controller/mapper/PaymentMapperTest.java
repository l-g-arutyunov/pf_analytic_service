package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.PaymentCategoryDto;
import com.devlife.pf_sql_controller.dto.PaymentDto;
import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.Payment;
import com.devlife.pf_sql_controller.entity.PaymentCategory;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("Payment mapper tests")
class PaymentMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    PaymentMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО Payment в Entity")
    void convertToEntity() {
        Payment referencePayment= new Payment();
        referencePayment.setId(1L);
        referencePayment.setProject(Project.builder()
                .id(1L)
                .build());
        referencePayment.setUser(User.builder().id(1L).build());
        referencePayment.setPaymentCategory(PaymentCategory.builder().id(1L).build());
        PaymentDto paymentDto = PaymentDto.builder()
                .id(1L)
                .paymentCategory(PaymentCategoryDto.builder().id(1L).build())
                .project(ProjectDto.builder().id(1L).build())
                .user(UserDto.builder().id(1L).build())
                .build();

        Payment payment = mapper.convertToEntity(paymentDto);
        assertEquals(referencePayment,payment);


    }

    @Test
    @DisplayName("Конвертируем Payment Employer в ДТО")
    void convertToDto() {
        PaymentDto referencePaymentDto = PaymentDto.builder()
                .id(1L)
                .paymentCategory(PaymentCategoryDto.builder().id(1L).build())
                .project(ProjectDto.builder().id(1L).build())
                .user(UserDto.builder().id(1L).build())
                .build();

        Payment paymentEmployer= new Payment();
        paymentEmployer.setId(1L);
        paymentEmployer.setProject(Project.builder().id(1L).build());
        paymentEmployer.setUser(User.builder().id(1L).build());
        paymentEmployer.setPaymentCategory(PaymentCategory.builder().id(1L).build());

        PaymentDto paymentDto = mapper.convertToDto(paymentEmployer);
        assertEquals(referencePaymentDto,paymentDto);
    }
}