package com.devlife.pf_sql_controller.mapper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("PaymentsReport Mapper Test")
class PaymentsReportMapperTest {
    @Autowired
    PaymentsReportMapper mapper;

    @Test
    void convertToResponse() {

    }
}