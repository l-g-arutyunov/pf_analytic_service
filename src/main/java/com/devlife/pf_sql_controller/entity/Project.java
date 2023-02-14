package com.devlife.pf_sql_controller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pf_project")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    private String description;

    @Column(name = "start_date", columnDefinition = "DATE")
    @EqualsAndHashCode.Include
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    @EqualsAndHashCode.Include
    private LocalDate endDate;

    @ManyToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employer_id")
    @EqualsAndHashCode.Include
    private Employer employer;

    @ManyToOne(targetEntity = ProjectType.class)
    @JoinColumn(name = "project_type_id")
    @EqualsAndHashCode.Include
    private ProjectType projectType;

    @ManyToOne(targetEntity = UserGroup.class)
    @JoinColumn(name = "user_group_id")
    @EqualsAndHashCode.Include
    private UserGroup userGroup;

    @OneToMany(targetEntity = ProjectRole.class, mappedBy = "project", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<ProjectRole> projectRoles;
}
