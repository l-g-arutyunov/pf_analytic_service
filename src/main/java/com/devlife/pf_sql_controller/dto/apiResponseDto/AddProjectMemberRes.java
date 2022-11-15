package com.devlife.pf_sql_controller.dto.apiResponseDto;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProjectMemberRes {
    UserDto user;
    UserGroupDto userGroup;
    ProjectRoleDto projectRoleDto;
}
