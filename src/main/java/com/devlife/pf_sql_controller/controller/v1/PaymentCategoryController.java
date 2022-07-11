package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.PaymentCategoryDto;
import com.devlife.pf_sql_controller.dto.PaymentDto;
import com.devlife.pf_sql_controller.service.PaymentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class PaymentCategoryController {

    private final PaymentCategoryService service;

    @PutMapping("paymentCategory")
    Long addPaymentCategory(@RequestBody PaymentCategoryDto paymentCategoryDto) {
        return service.addPaymentCategory(paymentCategoryDto);
    }

    @GetMapping("paymentCategory/{id}")
    PaymentCategoryDto getPaymentCategory(@PathVariable("id") Long id) {
        return service.getPaymentCategory(id);
    }

    @GetMapping("paymentCategories")
    List<PaymentCategoryDto> getAllPaymentCategories() {
        return service.getAllPaymentCategories();
    }

    @DeleteMapping("paymentCategory/{id}")
    Boolean deletePaymentCategoryById(@PathVariable("id") Long id) {
        return service.deletePaymentCategoryById(id);
    }

}
