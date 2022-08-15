package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.analyticFormDto.response.PaymentsFormResponse;
import com.devlife.pf_sql_controller.entity.view.PaymentsReport;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PaymentsReportMapper {
    private final ModelMapper mapper;

    public PaymentsReportMapper(ModelMapper mapper) {
        this.mapper = mapper;
        mapper.addMappings(new PropertyMap<PaymentsReport, PaymentsFormResponse>() {
            @Override
            protected void configure() {
                map().setPaymentDate(source.getDate());
            }
        });
    }

    public PaymentsFormResponse convertToResponse(PaymentsReport paymentsReport) {
        return mapper.map(paymentsReport, PaymentsFormResponse.class);
    }

}
