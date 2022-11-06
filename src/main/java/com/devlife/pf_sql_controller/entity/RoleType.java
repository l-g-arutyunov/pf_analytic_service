package com.devlife.pf_sql_controller.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pf_role_type")
@Data
public class RoleType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_type_id_seq")
    @SequenceGenerator(name = "role_type_id_seq", sequenceName = "role_type_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
