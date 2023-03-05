package com.devlife.pf_sql_controller.dto.asyncMessageModel;

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
public class UserAsyncModel implements Serializable {
    private Long id;
    private Long authUser;
    private LocalDate dateOfRegistration;
    private ProfileAsyncModel profile;
}
