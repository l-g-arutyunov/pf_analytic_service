package com.devlife.pf_sql_controller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pf_project")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne(targetEntity = ProjectType.class)
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;

    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;
}
