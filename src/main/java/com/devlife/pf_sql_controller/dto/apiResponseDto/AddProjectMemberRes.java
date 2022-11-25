package com.devlife.pf_sql_controller.dto.apiResponseDto;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectMemberRes {
    ProjectRoleDto projectRole;
}
