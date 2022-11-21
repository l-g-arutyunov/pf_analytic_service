package com.devlife.pf_sql_controller.dto.apiRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectMemberReq {
    Long userExternalId;
    LocalDate startDate;
    LocalDate endDate;
    Set<Long> projectRoleId;
    String description;
}
