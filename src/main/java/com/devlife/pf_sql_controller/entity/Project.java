package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

//    @Column(name = "employer_id")
//    private Long employerId;
//
//    @Column(name = "project_type_id")
//    private Long projectTypeId;
//
//    @Column(name = "user_group_id")
//    private Long userGroupId;

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
