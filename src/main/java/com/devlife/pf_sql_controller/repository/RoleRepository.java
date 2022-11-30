package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByUserGroupId(Long userGroupId);

}
