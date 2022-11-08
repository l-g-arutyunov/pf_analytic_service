package com.devlife.pf_sql_controller.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pf_employer")
@Data
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_id_seq")
    @SequenceGenerator(name = "employer_id_seq", sequenceName = "employer_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

}
