package com.devlife.pf_sql_controller.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentsReport Mapper Test")
class PaymentsReportMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    PaymentsReportMapper mapper;

    @Test
    void convertToResponse() {

    }
}