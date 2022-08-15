package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.analyticFormDto.request.PaymentsFormRequest;
import com.devlife.pf_sql_controller.dto.analyticFormDto.response.PaymentsFormResponse;
import com.devlife.pf_sql_controller.service.analytic.PaymentsAnalyticService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/analytics")
public class PaymentAnalyticsController {
    private final PaymentsAnalyticService paymentsAnalyticService;

    @PostMapping("getPayments")
    public List<PaymentsFormResponse> getPayments(
            @Parameter(name = "Get payments by params", required = true)
            @RequestBody
            PaymentsFormRequest paymentForm) {
        return paymentsAnalyticService.getPayments(paymentForm);
    }

    @PostMapping("getMeanPayment")
    public BigDecimal getMeanPayments(
            @Parameter(name = "Get mean payments by params", required = true)
            @RequestBody
            PaymentsFormRequest paymentForm) {
        return paymentsAnalyticService.getMeanPayments(paymentForm);
    }
}
