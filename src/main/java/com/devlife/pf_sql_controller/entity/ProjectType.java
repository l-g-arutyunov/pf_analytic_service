package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pf_project_type")
@Data
public class ProjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
