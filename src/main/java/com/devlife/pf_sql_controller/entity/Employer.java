package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employer")
@Data
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

}
