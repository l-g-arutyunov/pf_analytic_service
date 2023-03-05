package com.devlife.pf_sql_controller.dto;

import lombok.*;

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
    @EqualsAndHashCode.Exclude
    private Set<UserDto> users;
}
