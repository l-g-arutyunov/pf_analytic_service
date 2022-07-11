package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.PaymentDto;
import com.devlife.pf_sql_controller.entity.Payment;
import com.devlife.pf_sql_controller.mapper.PaymentMapper;
import com.devlife.pf_sql_controller.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;

    public Long addPayment(PaymentDto payment) {
        Payment savePayment = paymentRepository.save(mapper.convertToEntity(payment));
        if (savePayment != null) {
            return savePayment.getId();
        }
        return null;
    }

    public PaymentDto getPayment(Long id) {
        Payment payment = paymentRepository.getById(id);
        return mapper.convertToDto(payment);
    }

    public List<PaymentDto> getAllPayments() {
        List<Payment> paymentsList = paymentRepository.findAll();
        return paymentsList.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Boolean deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
        return !paymentRepository.existsById(id);
    }
}
