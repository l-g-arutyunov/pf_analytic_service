package com.devlife.pf_sql_controller.dto.apiRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectMemberReq {
    Long userExternalId;
    Set<Long> projectRoleId;
}
