package com.devlife.pf_sql_controller.dto.apiRequestDto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectReq {
    @Parameter(description = "project name", name = "name", required = true)
    @NotBlank(message = "must be fill")
    private String name;
    @Parameter(description = "project description", name = "description")
    private String description;
    @Parameter(description = "project start date ", name = "startDate")
    @NotNull(message = "must not be null")
    private LocalDate startDate;
    @Parameter(description = "project end date", name = "endDate")
    private LocalDate endDate;
    @Parameter(description = "employer id for project", name = "employerId")
    private Long employerId;
    @Parameter(description = "project type id. u can get project type id to api/v1/projectType GET", name = "projectTypeId", required = true)
    @NotNull(message = "must not be null")
    private Long projectTypeId;
    @Parameter(description = "user group id for project", name = "userGroupId")
    private Long userGroupId;
}
