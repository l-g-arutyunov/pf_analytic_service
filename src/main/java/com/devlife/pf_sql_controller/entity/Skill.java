package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skill")
@Data
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_role_skill",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "project_role_id"))
    private Set<ProjectRole> projectRoles;
}
