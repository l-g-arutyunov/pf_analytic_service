package com.devlife.pf_sql_controller.entity.embeddable;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProjectRoleSkillId implements Serializable {
    @Column(name="skill_id")
    private Long skillId;

    @Column(name="project_role_id")
    private Long projectRoleId;
}
