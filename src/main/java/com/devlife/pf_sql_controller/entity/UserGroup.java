package com.devlife.pf_sql_controller.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pf_user_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_id_seq")
    @SequenceGenerator(name = "user_group_id_seq", sequenceName = "user_group_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    private String description;

    @ManyToMany(mappedBy = "userGroups", targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<User> users;

}
