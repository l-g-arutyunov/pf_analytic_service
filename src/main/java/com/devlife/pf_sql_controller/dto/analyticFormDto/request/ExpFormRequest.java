package com.devlife.pf_sql_controller.dto.analyticFormDto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpFormRequest implements Serializable {
    private Long roleId;
    private Long projectId;
    private Long employerId;
    private Long userId;
    private LocalDate date;
}
