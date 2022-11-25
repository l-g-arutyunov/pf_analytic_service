package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByExternalId(Long externalId);

    Set<User> getUsersByExternalIdIn(Set<Long> externalIds);

    Optional<User> getByExternalId(Long externalId);



}
