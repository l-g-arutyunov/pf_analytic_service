package com.devlife.pf_sql_controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRoleDto implements Serializable {
    private Long id;
    private UserDto user;
    private RoleDto role;
    private ProjectDto project;
    private LocalDate startDate;
    private LocalDate endDate;
    private String roleLevel;
}
