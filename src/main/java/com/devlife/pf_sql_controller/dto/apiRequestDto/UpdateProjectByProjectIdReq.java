package com.devlife.pf_sql_controller.dto.apiRequestDto;

import io.swagger.v3.oas.annotations.Parameter;
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
    @Parameter(description = "project name", name = "name")
    private String name;
    @Parameter(description = "project description", name = "description")
    private String description;
    @Parameter(description = "project start date ", name = "startDate")
    private LocalDate startDate;
    @Parameter(description = "project end date", name = "endDate")
    private LocalDate endDate;
    @Parameter(description = "employer id for project", name = "employerId")
    private Long employerId;
    @Parameter(description = "project type id. u can get project type id to api/v1/projectType GET", name = "projectTypeId")
    private Long projectTypeId;
}
    
