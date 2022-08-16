package com.devlife.pf_sql_controller.dto.analyticFormDto.response;

import com.devlife.pf_sql_controller.dto.*;
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
public class PaymentsFormResponse implements Serializable {
    private RoleDto role;
    private ProjectDto project;
    private EmployerDto employer;
    private UserDto user;
    private UserGroupDto userGroup;
    private PaymentCategoryDto paymentCategory;
    private LocalDate paymentDate;
    private BigDecimal qty;
}
