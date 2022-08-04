package com.devlife.pf_sql_controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long externalId;
}
