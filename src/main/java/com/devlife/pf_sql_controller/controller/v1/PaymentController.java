package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.PaymentDto;
import com.devlife.pf_sql_controller.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class PaymentController {

    private final PaymentService service;

    @PutMapping("payment")
    Long addPayment(@RequestBody PaymentDto paymentDto) {
        return service.addPayment(paymentDto);
    }

    @GetMapping("payment/{id}")
    PaymentDto getPayment(@PathVariable("id") Long id) {
        return service.getPayment(id);
    }

    @GetMapping("payment")
    List<PaymentDto> getAllPayments() {
        return service.getAllPayments();
    }

    @DeleteMapping("payment/{id}")
    Boolean deletePaymentById(@PathVariable("id") Long id) {
        return service.deletePaymentById(id);
    }

}
