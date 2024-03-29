package com.devlife.pf_sql_controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto implements Serializable {
    private Long id;
    private ProjectDto project;
    private LocalDate date;
    private UserDto user;
    private PaymentCategoryDto paymentCategory;
    private BigDecimal qty;
}
