package com.devlife.pf_sql_controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class RoleTypeDto implements Serializable {
    private long id;
    private String name;
    private String description;

}
