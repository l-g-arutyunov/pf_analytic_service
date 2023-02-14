package com.devlife.pf_sql_controller.dto.asyncMessageModel;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUserLinkAsyncModel {
    private ProjectDto project;
    private UserDto user;
}
