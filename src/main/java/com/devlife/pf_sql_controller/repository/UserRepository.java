package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByExternalId(Long externalId);

    Optional<User> getByExternalId(Long externalId);

}
