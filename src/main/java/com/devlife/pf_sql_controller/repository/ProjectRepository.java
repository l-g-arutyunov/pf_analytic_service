package com.devlife.pf_sql_controller.repository;

import com.devlife.pf_sql_controller.entity.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Get set of project by userId
     * @param userId
     * @return set of project
     */
    @Query("select project from Project project " +
            "inner join UserGroupUser ugu on project.userGroup.id = ugu.userGroupUserId.userGroupId " +
            "where ugu.userGroupUserId.userId = :userId")
    Set<Project> getProjectsByUserId(@Param("userId") Long userId, Pageable pageable);

}
