package com.devlife.pf_sql_controller.repository;


import com.devlife.pf_sql_controller.dto.analyticFormDto.request.PaymentsFormRequest;
import com.devlife.pf_sql_controller.entity.view.PaymentsReport;

import java.util.List;

public interface PaymentCustomRepository {
    List<PaymentsReport> getPaymentsByParam(PaymentsFormRequest request);
}
