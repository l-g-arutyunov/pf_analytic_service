package com.devlife.pf_sql_controller.entity;

import com.devlife.pf_sql_controller.entity.embeddable.UserGroupUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "pf_user_group_user")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
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

