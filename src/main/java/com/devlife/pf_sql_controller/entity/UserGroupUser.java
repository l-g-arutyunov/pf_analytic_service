package com.devlife.pf_sql_controller.entity;

import com.devlife.pf_sql_controller.entity.embeddable.UserGroupUserId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_group_user")
@Data
public class UserGroupUser {

    @EmbeddedId
    private UserGroupUserId userGroupUserId;

    @Column(name = "is_owner")
    private Boolean isOwner;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;
}

