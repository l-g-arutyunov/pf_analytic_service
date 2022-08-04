package com.devlife.pf_sql_controller.entity;

import com.devlife.pf_sql_controller.entity.embeddable.ProjectRoleSkillId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="project_role_skill")
@Data
public class ProjectRoleSkill {
    @EmbeddedId
    private ProjectRoleSkillId projectRoleSkillId;
}
