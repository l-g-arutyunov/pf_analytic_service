package com.devlife.pf_sql_controller.dto;

import com.devlife.pf_sql_controller.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroupDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Set<RoleDto> roles;
}
