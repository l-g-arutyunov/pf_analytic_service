package com.devlife.pf_sql_controller.dto.apiRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProjectByProjectIdReq {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long employerId;
    private Long projectTypeId;
}
