package com.devlife.pf_sql_controller.dto;

import com.devlife.pf_sql_controller.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RoleDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long userGroupId;
    private RoleType roleType;
}
