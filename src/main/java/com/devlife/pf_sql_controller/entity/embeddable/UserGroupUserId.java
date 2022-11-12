package com.devlife.pf_sql_controller.entity.embeddable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserGroupUserId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_group_id")
    private Long userGroupId;
}
