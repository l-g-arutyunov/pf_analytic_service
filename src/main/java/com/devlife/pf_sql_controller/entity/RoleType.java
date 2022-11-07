package com.devlife.pf_sql_controller.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "pf_role_type")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
