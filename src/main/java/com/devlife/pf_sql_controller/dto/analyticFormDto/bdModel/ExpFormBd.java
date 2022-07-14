package com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpFormBd {
    private LocalDate startDate;
    private LocalDate endDate;
}
