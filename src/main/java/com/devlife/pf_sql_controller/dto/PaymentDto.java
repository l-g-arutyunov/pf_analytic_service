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
    private Long project_id;
    private LocalDate date;
    private Long user_id;
    private Long pay_category_id;
    private BigDecimal qty;
}
