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
public class ProfileAsyncModel implements Serializable {
    private LocalDate dateOfBirth;
    private String firstName;
    private Long id;
    private String lastName;
    private String middleName;
    private String nickname;
    private String personalInformation;
}
