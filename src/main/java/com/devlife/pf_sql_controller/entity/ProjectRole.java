package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project_role")
@Data
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "role_id")
//    private Long roleId;
//
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "project_id")
//    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

}
