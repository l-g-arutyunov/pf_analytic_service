package com.devlife.pf_sql_controller.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pf_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "user_group_id")
    private Long userGroupId;

    @ManyToOne
    @JoinColumn(name = "role_type_id")
    private RoleType roleType;
}
