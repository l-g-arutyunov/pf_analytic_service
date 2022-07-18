package com.devlife.pf_sql_controller.dto.analyticFormDto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpFormResponse{
    private Integer dayExp;
    private Integer monthExp;
    private Integer yearExp;
}
