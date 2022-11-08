package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pf_skill")
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_id_seq")
    @SequenceGenerator(name = "skill_id_seq", sequenceName = "skill_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pf_project_role_skill",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "project_role_id"))
    private Set<ProjectRole> projectRoles;

    @Column(name = "external_id")
    private Long externalId;
}
