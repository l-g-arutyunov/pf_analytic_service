package com.devlife.pf_sql_controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserGroupUserDto implements Serializable {
    private Long userId;
    private Long groupId;
    private Boolean isOwner;
    private Boolean isActive;
    private LocalDate date;
}
