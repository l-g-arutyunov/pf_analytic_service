package com.devlife.pf_sql_controller.service.analytic;

import com.devlife.pf_sql_controller.dto.analyticFormDto.request.PaymentsFormRequest;
import com.devlife.pf_sql_controller.dto.analyticFormDto.response.PaymentsFormResponse;
import com.devlife.pf_sql_controller.entity.view.PaymentsReport;
import com.devlife.pf_sql_controller.mapper.PaymentsReportMapper;
import com.devlife.pf_sql_controller.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentsAnalyticService {
    private final PaymentRepository paymentRepository;
    private final PaymentsReportMapper paymentsReportMapper;

    private static final String START_DATE_IS_REQUIRE = "Start date is require.";
    private static final String END_DATE_IS_REQUIRE = "End date is require.";
    public List<PaymentsFormResponse> getPayments(PaymentsFormRequest paymentsFormRequest) {
        validate(paymentsFormRequest);
        List<PaymentsReport> paymentsByParam = paymentRepository.getPaymentsByParam(paymentsFormRequest);

        return paymentsByParam.stream().map(paymentsReportMapper::convertToResponse).collect(Collectors.toList());
    }

    public BigDecimal getMeanPayments(PaymentsFormRequest paymentsFormRequest) {
        validate(paymentsFormRequest);
        List<PaymentsReport> paymentsByParam = paymentRepository.getPaymentsByParam(paymentsFormRequest);

        BigDecimal sumOfPaymentQty = paymentsByParam.stream().map(PaymentsReport::getQty).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        long countOfPayments = paymentsByParam.size();

        return sumOfPaymentQty.divide(BigDecimal.valueOf(countOfPayments), RoundingMode.UP);
    }

    private void validate(PaymentsFormRequest paymentsFormRequest) {
        Objects.requireNonNull(paymentsFormRequest.getStartDate(), START_DATE_IS_REQUIRE);
        Objects.requireNonNull(paymentsFormRequest.getEndDate(), END_DATE_IS_REQUIRE);
    }

}
