package com.devlife.pf_sql_controller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pf_project_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_type_id_seq")
    @SequenceGenerator(name = "project_type_id_seq", sequenceName = "project_type_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;
}
