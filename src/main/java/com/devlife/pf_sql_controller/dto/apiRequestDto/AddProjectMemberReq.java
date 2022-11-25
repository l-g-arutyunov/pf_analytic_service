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
public class AddProjectMemberReq {
    Long userExternalId;
    LocalDate startDate;
    LocalDate endDate;
    Long roleId;
    String roleLevel;
    String description;
}
