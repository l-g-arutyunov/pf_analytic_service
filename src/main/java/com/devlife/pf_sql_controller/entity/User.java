package com.devlife.pf_sql_controller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pf_user")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nickname")
    @EqualsAndHashCode.Include
    private String nickName;

    @Column(name = "external_id")
    @EqualsAndHashCode.Include
    private Long externalId;

    @Column(name = "first_name")
    @EqualsAndHashCode.Include
    private String firstName;

    @Column(name = "last_name")
    @EqualsAndHashCode.Include
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JoinTable(name = "pf_user_group_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id"))
    private Set<UserGroup> userGroups;
}