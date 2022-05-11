package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
